package doc.jockey.runners

import doc.jockey.rendering.Html
import org.scalatest.WordSpec
import scala.reflect.io._

class OutputWriterAcceptanceSpec extends WordSpec {
  class DummyClass

  "Output files take their name and package from the spec class" in {
    new OutputWriter(this.getClass).close

    assert(File("target/doc-jockey/doc/jockey/runners/OutputWriterAcceptanceSpec.html").slurp === Html.header + Html.footer)
  }

  "Multiple specify() calls in one Spec class all write to the same output file" in {
    val expected: String = Html.header + <p>Output of specify() one</p> + <p>Output of specify() two</p> + Html.footer

    val writer = new OutputWriter((new DummyClass).getClass)
    writer.write(<p>Output of specify() one</p>)
    writer.write(<p>Output of specify() two</p>)
    writer.close

    assert(File("target/doc-jockey/doc/jockey/runners/DummyClass.html").slurp === expected)
  }
}