import sbt._
import Keys._

object Build extends Build {

  def sharedSettings = Seq(
    scalaVersion:= "2.10.2",
    scalacOptions += "-deprecation",
    testOptions in Test += Tests.Argument("-oF"),
    libraryDependencies ++= {
      Seq(
        "io.shaka" %% "naive-http-server" % "15",
        "junit" % "junit" % "4.8" % "test->default",
        "org.scalatest" %% "scalatest" % "1.9.1",
        "org.mockito" % "mockito-core" % "1.9.5"
      )
    }
  )

  lazy val main = Project(id = "main", base = file(".")).settings(sharedSettings: _*)
}
