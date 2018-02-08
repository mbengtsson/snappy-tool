import com.typesafe.sbt.SbtNativePackager.packageArchetype

name := "snappy"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(JavaAppPackaging)
libraryDependencies += "org.xerial.snappy" % "snappy-java" % "1.1.7.1"
