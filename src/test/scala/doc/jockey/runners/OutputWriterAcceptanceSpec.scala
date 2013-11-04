package doc.jockey.runners

import doc.jockey.rendering._
import org.scalatest.WordSpec

class OutputWriterAcceptanceSpec extends WordSpec with HtmlAssertions {
  class DummyClass

  val defaultHeader =
    <header>
      <link href='../../../css/doc-jockey.css' rel='stylesheet' type='text/css'/>
    </header>

  "Output files take their name and package from the spec class" in {
    new OutputWriter(this.getClass).close

    assertEqual(xmlFromOutputFile(classOf[OutputWriterAcceptanceSpec]), <html>{defaultHeader}<body/></html>)
  }

  "Multiple specify() calls in one Spec class all write to the same output file" in {
    val expected =
      <html>
        {defaultHeader}
        <body>
          <p>Output of specify() one</p>
          <p>Output of specify() two</p>
        </body>
      </html>

    val writer = new OutputWriter((new DummyClass).getClass)
    writer.write(<p>Output of specify() one</p>)
    writer.write(<p>Output of specify() two</p>)
    writer.close

    assertEqual(xmlFromOutputFile(classOf[DummyClass]), expected)
  }

  class CssDummyClass

  "CSS scripts are expected at the top of the source tree" in {
    new OutputWriter(classOf[CssDummyClass]).close

    assertContains(xmlFromOutputFile(classOf[CssDummyClass]), "link", <link href='../../../css/doc-jockey.css' rel='stylesheet' type='text/css'/>)
  }
}