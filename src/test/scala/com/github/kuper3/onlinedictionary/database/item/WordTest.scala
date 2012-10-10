package com.github.kuper3.onlinedictionary.database.item

import org.scalatest.junit.AssertionsForJUnit
import org.junit._
import java.sql.SQLException

class WordTest extends AssertionsForJUnit {

  val word = Word("test123", "тест123");
  val emptyWord = Word("", "") 

  @Test def verifyInsert: Unit = {
    intercept[SQLException] {
      Word.insert(emptyWord)
    }
    assert(Word.insert(word) >= 1)
    Word.delete(word)
  }

  @Test def verifyFetch: Unit = {
    Word.insert(word)
    assert(Word.fetch.length >= 1)
    Word.delete(word)
  }

}

object WordTest {

  @BeforeClass def setUp: Unit = {
    // if system had JUnit and Spec2 tests, it would crash during createTable. Probably this is PostgreSQL specific behaviour.
    /*
        * Well, it does avoid any "misbehavior", it's just not producing exactly
	* the error message you expect.  If you have two sessions trying to create
	* the same table name at exactly the same time, this is a likely result.
	* They both have to create pg_type entries for the table's row type,
	* so they can collide on that as well as on the table name itself.
	*
	* There have been some unexplained reports of similar error messages in
	* cases where it didn't seem that any concurrent creation could be going
	* on.  But I don't see any reason to think there's anything very
	* mysterious here, if you do have sessions trying to create the same table
	* concurrently.
        *
        * from http://archives.postgresql.org/pgsql-bugs/2010-03/msg00082.php
     */
    //Word.createTable
  }

  @AfterClass def teardown: Unit = {
    // Spec2 will run first and create table.
    //Word.dropTable
    Word.clearTable
  }
}
