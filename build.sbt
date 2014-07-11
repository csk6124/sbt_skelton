import AssemblyKeys._
import xerial.sbt.Pack._

assemblySettings

packSettings

name := "June"

version in ThisBuild := "0.7.1"

autoScalaLibrary in ThisBuild := true

organization in ThisBuild := "io.June"

scalaVersion in ThisBuild := "2.10.4"

scalacOptions in ThisBuild ++= Seq("-deprecation", "-unchecked", "-feature")

scalacOptions in (ThisBuild, Test) ++= Seq("-Yrangepos")

javacOptions in ThisBuild ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:deprecation", "-Xlint:unchecked")

lazy val hello = taskKey[Unit]("Prints 'Hello World'")

hello := println("hello world!")