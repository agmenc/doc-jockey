package doc.jockey.rendering

import doc.jockey.rendering.Prettifier._
import org.scalatest.Suite
import scala.xml._
import scala.reflect.io._
import scala.Pimps._

trait HtmlAssertions { self: Suite =>
  val writer = new TestOutputWriter(self.getClass)

  // TODO - CAS - 11/10/2013 - Side-by-side comparison in IntelliJ - ScalaTest IntelliJ plugin?
  
  def assertEqual(actual: NodeSeq, expected: NodeSeq, testName: Option[String] = None) = compare(actual, expected, testName)
  def assertEqual(actual: String, expected: NodeSeq) = compare(actual, expected)

  private def compare(actual: String, expected: String, testName: Option[String] = None) =
    if (actual != expected) {
      testName.map(name => {
        writer.write(s"$name.actual", actual)
        writer.write(s"$name.expected", expected)
      })

      // TODO - CAS - 19/10/2013 - Throw an exception that IntelliJ recognises, so that it displays a side-by-side comparison of the differences
      assert(actual === expected)
    }
}

// TODO - CAS - 26/10/2013 - Extract all paths into an overrideable single location
class TestOutputWriter(callingSpec: Class[_]) {
  private val baseDir = Path(".") / "src" / "test" / "scala"
  private val packageDir = callingSpec.getPackage.getName replaceAll ("\\.", File.separator)

  def write(fileName: String, contents: String): Unit = file(fileName).writeAll(contents)

  // TODO - CAS - 26/10/2013 - Extract this into a common file find-or-create location
  private def file(fileName: String): File = tee((dir / fileName).toFile)(_.createFile())
  private def dir: Directory = tee((baseDir / packageDir).toDirectory)(_.createDirectory())


}