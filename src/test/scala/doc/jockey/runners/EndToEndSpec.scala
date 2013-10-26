package doc.jockey.runners

import doc.jockey.rendering.HtmlAssertions
import example.project.fixture.SupportedProductTypes.Expected
import example.project.fixture._
import example.project.main._
import org.scalatest._
import org.scalatest.events.Event

class EndToEndSpec extends WordSpec with HtmlAssertions {
  "We can run a spec in a different class, and check the output file" in {
    val outputFile = new OutputWriter(classOf[SomeDocJockeySpec]).file
    outputFile.deleteIfExists()
    assert(!outputFile.exists)

    runSpec(new SomeDocJockeySpec)

    assertEqual(outputFile.slurp(), expectedHtml)
  }

  class SomeDocJockeySpec extends DocJockeySpec {
    specify("Trade clearing engine supports correct workflows and product types")(
      ComputerIs(true),
      SupportedWorkflows(LchFcm, List(Manual, Netting)),
      SupportedProductTypes(
        List(Vanilla, Fra, Vns),
        List(
          Expected("some desc", LchFcm, true, false, true),
          Expected("some other desc", LchScm, true, true, false)
        )
      )
    )

    specify("We can have a second, if fairly pointless, doc-jockey spec in the same Spec class")(
      ComputerIs(true)
    )
  }

  def runSpec(spec: EndToEndSpec.this.type#SomeDocJockeySpec) {
    spec.run(None, new VeryQuietReporter(), new Stopper() {}, new Filter(None, Set.empty), Map(), None, new Tracker())
  }

  class VeryQuietReporter extends Reporter {
    def apply(event: Event) = {} // swallow
  }

  val expectedHtml =
    <html>
    <header/>
    <body>
      <h2>We can have a second, if fairly pointless, doc-jockey spec in the same Spec class</h2>
      <table>
        <tr><td>Computer is</td><td>on</td></tr>
      </table>
      <h2>Trade clearing engine supports correct workflows and product types</h2>
      <table>
        <tr><td>Computer is</td> <td>on</td></tr>
      </table>
      <table>
        <tr><td>Supported clearing workflows</td> <td>LCH-FCM</td> <td>Manual, Netting</td></tr>
      </table>
      <table>
        <tr><td>Product types supported</td></tr>
        <tr><td>Description</td><td>Clearing house</td><td>Vanilla</td><td>FRA</td><td>VNS</td></tr>
        <tr><td>some desc</td><td>LCH-FCM</td><td>✓</td><td>-</td><td>✓</td></tr>
        <tr><td>some other desc</td><td>LCH-SCM</td><td>✓</td><td>✓</td><td>-</td></tr>
      </table>
    </body>
    </html>
}