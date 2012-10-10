package com.github.kuper3.onlinedictionary

import org.scalatest.junit.AssertionsForJUnit
import org.scalatra.test.specs2._

class MainScalatraServletTest extends MutableScalatraSpec {
  
  addServlet(classOf[MainScalatraServlet], "/*")

  "GET / on MainScalatraServlet" should {
    "return status 200" in {
      get("/") {
        status must_== 200
      }
    }
  }
  
  "GET /show on MainScalatraServlet" should {
    "return status 200" in {
      get("/show") {
        status must_== 200
      }
    }
  }
  
  "GET /add on MainScalatraServlet" should {
    "return status 400 when /add?e=&t=" in {
      get("/add?e=&t=") {
        status must_== 400
      }
    }
    "return status 400 when /add?e=" in {
      get("/add?e=") {
        status must_== 400
      }
    }
    "return status 400 when /add?t=" in {
      get("/add?t=") {
        status must_== 400
      }
    }

    "return status 400 when /add" in {
      get("/add") {
        status must_== 400
      }
    }
  }

  "GET /random on MainScalatraServlet" should {
    "return status 200" in {
      get("/random") {
        status must_== 200
      }
    }
  }
}
