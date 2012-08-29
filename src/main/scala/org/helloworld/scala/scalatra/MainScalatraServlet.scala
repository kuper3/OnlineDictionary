package org.helloworld.scala.scalatra

import org.scalatra._
import scalate.ScalateSupport
import org.helloworld.scala.scalatra.database.item.Word

class MainScalatraServlet extends ScalatraServlet with ScalateSupport {
  
  get("/") {
    <h1>Hello World!</h1>
  }
  
  get("/add") {
    val e = params("e")
    val t = params("t")
    Word.insertWord(new Word(e,t))
    
    <h1>Hello, {t} </h1>
    
  }
  
  get("/show") {
    val words = Word.fetchWords
    var text : String = ""
    words.foreach( w => text += w.englishWord +"\t" +w.translation + "\n")
    text
  }
  
  get("/init") {
    Word.createTable
  }
  
  get("/clear") {
    Word.dropTable
  }
 

}