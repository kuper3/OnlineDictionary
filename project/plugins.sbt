libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.11.1"))



resolvers += Classpaths.typesafeResolver


addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.0-RC1")

addSbtPlugin("com.typesafe.startscript" % "xsbt-start-script-plugin" % "0.5.0")
