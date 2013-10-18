package doc.jockey.runners

import doc.jockey.rendering._
import org.scalatest.WordSpec
import scala.reflect.io._

class OutputWriterAcceptanceSpec extends WordSpec with HtmlAssertions {
  class DummyClass

  "Output files take their name and package from the spec class" in {
    new OutputWriter(this.getClass).close

    assertEqual(File("target/doc-jockey/doc/jockey/runners/OutputWriterAcceptanceSpec.html").slurp, <html><header/><body/></html>)
  }

  "Multiple specify() calls in one Spec class all write to the same output file" in {
    val expected =
      <html>
        <header/>
        <body>
          <p>Output of specify() one</p>
          <p>Output of specify() two</p>
        </body>
      </html>

    val writer = new OutputWriter((new DummyClass).getClass)
    writer.write(<p>Output of specify() one</p>)
    writer.write(<p>Output of specify() two</p>)
    writer.close

    assertEqual(File("target/doc-jockey/doc/jockey/runners/DummyClass.html").slurp, expected)
  }
}