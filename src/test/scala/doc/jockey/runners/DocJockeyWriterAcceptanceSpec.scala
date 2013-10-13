package doc.jockey.runners

import doc.jockey.rendering.Prettifier._
import doc.jockey.rendering.Html
import org.scalatest.WordSpec
import scala.reflect.io._

class DocJockeyWriterAcceptanceSpec extends WordSpec {
  "Output files are written to the target/doc-jockey directory" in {
    val expected: String = Html(<p>Some exciting output</p>)

    DocJockeyWriter.write(<p>Some exciting output</p>)

    assert(File("target/doc-jockey/output.html").slurp === expected)
  }
}