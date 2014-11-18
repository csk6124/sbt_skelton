import sbt._
import Keys._

object MyBuild extends Build {

    private val commonSettings = net.virtualvoid.sbt.graph.Plugin.graphSettings
    lazy val root = project
       .in(file("."))
       .settings(commonSettings:_*)
       .settings(
            libraryDependencies += "org.fluentd" % "fluent-logger" % "0.2.10",
            libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.0-R4",
            libraryDependencies += "org.controlsfx" % "openjfx-dialogs" % "1.0.2",
            libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.42.2",
            libraryDependencies += "org.testng" % "testng" % "6.8.8"
            //unmanagedBase := baseDirectory.value / "libs"
       )
       .aggregate(core, util)

    lazy val core = project
      .in(file("core"))
      .settings(commonSettings:_*)
      .settings(
        libraryDependencies += "org.fluentd" % "fluent-logger" % "0.2.10"
      )

    lazy val util = project
      .in(file("util"))
      .settings(commonSettings:_*)

}