package com.github.kuper3.onlinedictionary.database.item

import org.scalatest.junit.AssertionsForJUnit
import org.junit._
import java.sql.SQLException

class WordTest extends AssertionsForJUnit {

  val word = Word("test123", "тест123");
  val emptyWord = Word("", "")

  @Before def setUp: Unit = {
    Word.createTable
  }

  @After def tearDown: Unit = {
    Word.dropTable
  }
  @Test def verifyInsert = {
    intercept[SQLException] {
      Word.insert(emptyWord)
    }
    assert(Word.insert(word) == 1)
  }

  @Test def verifyFetch = {
    Word.insert(word)
    assert(Word.fetch.length == 1)
  }

}
