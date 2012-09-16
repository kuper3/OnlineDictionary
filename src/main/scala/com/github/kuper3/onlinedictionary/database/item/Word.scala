package com.github.kuper3.onlinedictionary.database.item

import net.noerd.prequel.DatabaseConfig
import net.noerd.prequel.SQLFormatterImplicits._
import net.noerd.prequel.ResultSetRowImplicits._

case class Word(englishWord: String, translation: String) {
  override def toString = englishWord + " = " + translation
}

object Word {
  val database = DatabaseConfig(
    driver = "org.postgresql.Driver",
    jdbcURL = "jdbc:postgresql://localhost:5432/test1?user=postgres&password=pwd")

  def insertWord(word: Word): Unit = {
    database.transaction { tx =>
      tx.execute(
        "insert into Words( englishWord, translation ) values( ?, ?)",
        word.englishWord, word.translation)
    }
  }

  def insertWords(words: Seq[Word]): Unit = {
    database.transaction { tx =>
      tx.executeBatch("insert into Words( englishWord, translation ) values( ?, ?)") { statement =>
        words.foreach { word =>
          statement.executeWith(word.englishWord, word.translation)
        }
      }
    }
  }

  def fetchWords: Seq[Word] = {
    database.transaction { tx =>
      tx.select("select englishWord, translation from Words") { r =>
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

  def createTable: Unit = {
    database.transaction { tx =>
      tx.execute("CREATE TABLE IF NOT EXISTS Words (englishWord varchar(25), translation text);")
    }
  }

  def dropTable: Unit = {
    database.transaction { tx =>
      tx.execute("DROP TABLE IF EXISTS Words ")
    }
  }
}