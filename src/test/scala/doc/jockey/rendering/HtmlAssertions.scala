package doc.jockey.rendering

import doc.jockey.rendering.Prettifier._
import org.scalatest.Suite
import scala.xml.NodeSeq

trait HtmlAssertions { self: Suite =>
  // TODO - CAS - 11/10/2013 - Side-by-side comparison in IntelliJ - ScalaTest IntelliJ plugin?
  
  def assertEqual(actual: NodeSeq, expected: NodeSeq) = compare(actual, expected)

  private def compare(actual: String, expected: String) = assert(actual === expected)
}