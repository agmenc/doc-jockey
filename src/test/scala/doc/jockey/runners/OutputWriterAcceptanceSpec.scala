package doc.jockey.runners

import doc.jockey.rendering._
import org.scalatest.WordSpec
import doc.jockey.rendering.HtmlSpec._

class OutputWriterAcceptanceSpec extends WordSpec with HtmlAssertions {
  class DummyClass

  "Output files take their name and package from the spec class" in {
    new OutputWriter(this.getClass).close()

    assertEqual(xmlFromOutputFile(classOf[OutputWriterAcceptanceSpec]), <html>{headerFor(classOf[OutputWriterAcceptanceSpec])}<body><div class="container"/></body></html>)
  }

  "Multiple specify() calls in one Spec class all write to the same output file" in {
    val expected =
      <html>
        {headerFor(classOf[DummyClass])}
        <body>
          <div class="container">
            <p>Output of specify() one</p>
            <p>Output of specify() two</p>
          </div>
        </body>
      </html>

    val writer = new OutputWriter((new DummyClass).getClass)
    writer.write(<p>Output of specify() one</p>)
    writer.write(<p>Output of specify() two</p>)
    writer.close()

    assertEqual(xmlFromOutputFile(classOf[DummyClass]), expected)
  }

  class CssDummyClass

  "CSS scripts are expected at the top of the source tree" in {
    new OutputWriter(classOf[CssDummyClass]).close

    assertContains(xmlFromOutputFile(classOf[CssDummyClass]), "link", <link href='../../../css/doc-jockey.css' rel='stylesheet' type='text/css'/>)
  }
}