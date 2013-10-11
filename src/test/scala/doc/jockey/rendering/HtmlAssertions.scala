package doc.jockey.rendering

import scala.xml.NodeSeq
import scala.xml.PrettyPrinter
import org.scalatest.Suite

trait HtmlAssertions { self: Suite =>
  // TODO - CAS - 11/10/2013 - Step one: prettify
  // TODO - CAS - 11/10/2013 - Step two: side-by-side comparison in IntelliJ - ScalaTest IntelliJ plugin?
  
  private lazy val prettifier = new PrettyPrinter(1000, 4)

  def assertEqual(actual: NodeSeq, expected: NodeSeq) = compare(actual, expected)

  private def compare(actual: String, expected: String) = assert(actual === expected)

  implicit def prettify(ns: NodeSeq): String = {
    val buffy = new StringBuilder()
    prettifier.format(ns.head, buffy)
    buffy.toString()
  }
}