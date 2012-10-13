package com.github.kuper3.onlinedictionary.database.item

import net.noerd.prequel.DatabaseConfig
import net.noerd.prequel.SQLFormatterImplicits._
import net.noerd.prequel.ResultSetRowImplicits._
import java.net.URI

case class Word(englishWord: String, translation: String) {
  override def toString = englishWord + " : " + translation
}

object Word {

  val DbDriver = "org.postgresql.Driver"
  private def getDb = {
     if (System.getenv("DATABASE_URL") != null) {
       val dbUri = new URI(System.getenv("DATABASE_URL"))
       val dbUsername = dbUri.getUserInfo().split(":")(0)
       val dbPassword = dbUri.getUserInfo().split(":")(1)
       DatabaseConfig(driver = DbDriver,
              jdbcURL = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + ":" + dbUri.getPort(),
              username = dbUsername,
              password = dbPassword)
     } else {
        //"jdbc:postgresql://localhost:5432/test1?user=postgres&password=pwd"
	DatabaseConfig(
	    driver = DbDriver,
	    jdbcURL = "jdbc:postgresql://localhost:5432/test1?user=postgres&password=pwd")

     }           
    
  }
  
  val database = getDb

  def insert(word: Word) = {
    database.transaction { tx =>
      tx.execute(
        "INSERT INTO Words( englishWord, translation ) VALUES( ?, ?)",
        word.englishWord, word.translation)
    }
  }

  def fetch: Seq[Word] = {
    database.transaction { tx =>
      tx.select("SELECT englishWord, translation FROM words") { r =>
        new Word(r, r)
      }
    }
  }
  
  def random = {
    database.transaction { tx =>
      tx.select("SELECT englishWord, translation FROM words ORDER BY RANDOM() LIMIT 1") { r =>
        new Word(r,r)
      }
    }
  }

  // util method for testing
  def delete(word: Word) = {
    database.transaction { tx =>
      tx.execute("DELETE FROM words WHERE englishWord=? and translation=?",
      word.englishWord, word.translation)
    }
  }


  def createTable: Unit = {
    database.transaction { tx =>
      tx.execute("CREATE TABLE IF NOT EXISTS words (englishWord varchar(25) NOT NULL CHECK(englishWord <> ''), translation text NOT NULL CHECK(translation <> ''))")
    }
  }

  def dropTable: Unit = {
    database.transaction { tx =>
      tx.execute("DROP TABLE IF EXISTS words ")
    }
  }

  def clearTable: Unit = {
    database.transaction { tx =>
      tx.execute("TRUNCATE TABLE IF EXISTS words ")
    }
  }
}
