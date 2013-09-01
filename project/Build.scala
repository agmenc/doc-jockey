import sbt._
import Keys._

object Build extends Build {

  def sharedSettings = Seq(
    scalaVersion:= "2.10.2",
    scalacOptions += "-deprecation",
    libraryDependencies ++= {
      Seq(
        "junit" % "junit" % "4.8" % "test->default",
        "org.scalatest" %% "scalatest" % "1.9.1"
      )
    }
  )

  lazy val main = Project(id = "main", base = file(".")).settings(sharedSettings: _*)
}
