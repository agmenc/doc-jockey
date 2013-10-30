package doc.jockey.rendering

import doc.jockey.files.FileOps
import doc.jockey.rendering.Prettifier._
import org.scalatest.Suite
import scala.Pimps._
import scala.reflect.io._
import scala.xml._

trait HtmlAssertions { self: Suite =>
  val writer = new TestOutputWriter(self.getClass)

  def assertEqual(actual: NodeSeq, expected: NodeSeq, testName: Option[String] = None) = compare(actual, expected, testName)
  def assertEqual(actual: String, expected: NodeSeq) = compare(actual, expected)

  implicit def toOption(string: String): Option[String] = Some(string)

  private def compare(actual: String, expected: String, testName: Option[String] = None) =
    if (actual != expected) {
      testName.map(name => {
        writer.write(s"$name.actual", actual)
        writer.write(s"$name.expected", expected)
      })

      // TODO - CAS - 19/10/2013 - Throw an exception that IntelliJ recognises, so that it displays a side-by-side comparison of the differences. Do we need a ScalaTest IntelliJ plugin?
      assert(actual === expected)
    }
}

class TestOutputWriter(callingSpec: Class[_]) extends FileOps(callingSpec, Path(".") / "src" / "test" / "scala") {
  def write(fileName: String, contents: String): Unit = file(fileName).writeAll(contents)
  private def file(fileName: String): File = tee((dir / fileName).toFile)(_.createFile())
}