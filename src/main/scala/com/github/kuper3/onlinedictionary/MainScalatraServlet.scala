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
    val msg = "The request cannot be fulfilled due to bad syntax."
    val e = params.getOrElse("e",halt(status = 400,body = msg))
    val t = params.getOrElse("t",halt(status = 400,body = msg))
    if (!e.isEmpty() && !t.isEmpty()) {
    	Word.insert(new Word(e,t))
    } else {
      halt(status = 400,body = msg)
    }
  }
  
  get("/show") {
    val words = Word.fetch
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
