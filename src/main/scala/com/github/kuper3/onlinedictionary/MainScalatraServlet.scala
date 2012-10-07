package com.github.kuper3.onlinedictionary

import org.scalatra._
import scalate.ScalateSupport
import com.github.kuper3.onlinedictionary.database.item.Word

class MainScalatraServlet extends ScalatraServlet { 
  
  before() {
    Word.createTable
  }
  
  get("/") {
    <h1>Hello World!</h1>
  } 
  
  get("/add") {
    val e = params("e")
    val t = params("t")
    if (!e.isEmpty() && !t.isEmpty()) {
    	Word.insertWord(new Word(e,t))
    }
  }
  
  get("/show") {
    val words = Word.fetchWords
    var text = ""
    words.foreach( w => text += w.englishWord +"\t" +w.translation + "\n")
    text
  }
  
  get("/random") {
    Word.random.mkString
  }
  
  get("/clear") {
    Word.dropTable
  }

}