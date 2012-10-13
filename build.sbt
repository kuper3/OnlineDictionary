import com.typesafe.startscript.StartScriptPlugin

organization := "com.github.kuper3.onlinedictionary"

name := "OnlineDictionary"

version := "0.1.0"

scalaVersion := "2.9.2"

seq(webSettings :_*)

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

classpathTypes ~= (_ + "orbit")

// Runtime Dependencies
libraryDependencies ++= Seq(
  "org.scalatra" % "scalatra" % "2.1.0",
  "org.scalatra" % "scalatra-scalate" % "2.1.0",
  "joda-time" % "joda-time" % "2.1",
  "org.joda" % "joda-convert" % "1.2",
  "commons-dbcp" % "commons-dbcp" % "1.4",
  "commons-lang" % "commons-lang" % "2.6",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime", 
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.3.v20120416" % "container",
  "org.eclipse.jetty" % "jetty-server" % "8.1.3.v20120416" % "container",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.3.v20120416",
  "org.eclipse.jetty" % "jetty-server" % "8.1.3.v20120416",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile;container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
)

// Test Dependencies
libraryDependencies ++= Seq(
  "org.scalatra" % "scalatra-specs2" % "2.1.0" % "test",
  "org.scalatest" %% "scalatest" % "1.8" % "test",
  "com.novocode" % "junit-interface" % "0.8" % "test->default",
  "junit" % "junit" % "4.8.1" % "test",
  "org.eclipse.jetty" % "test-jetty-servlet" % "8.1.3.v20120416" % "test"
)
