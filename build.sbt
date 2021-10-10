name := "jsonsearch"

version := "0.1"

scalaVersion := "2.13.6"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
libraryDependencies += "junit" % "junit" % "4.13.2" % "test"
