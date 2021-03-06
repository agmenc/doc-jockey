package doc.jockey.runners

import doc.jockey.rendering.HtmlAssertions
import doc.jockey.runners.scalatest.ScalaTestManipulation
import example.project.fixture.SupportedProductTypes.Expected
import example.project.fixture._
import example.project.main._
import org.scalatest._

class EndToEndSpec extends WordSpec with HtmlAssertions with ScalaTestManipulation {
  "We can run a spec in a different class, and check the output file" in {
    val outputFile = new OutputWriter(classOf[SomeDocJockeySpec]).file
    outputFile.deleteIfExists()
    assert(!outputFile.exists)

    runSpec(new SomeDocJockeySpec)

    assertEqual(xmlFromOutputFile(classOf[SomeDocJockeySpec]), expectedHtml, "EndToEnd")
  }

  class SomeDocJockeySpec extends DocJockeySpec {
    specify("Trade clearing engine supports correct workflows and product types")(
      ComputerIs(true),
      SupportedWorkflows(LchFcm, List(Manual, Netting)),
      SupportedProductTypes(
        List(Vanilla, Fra, Vns),
        List(
          Expected("some desc", LchFcm, true, false, true),
          Expected("some other desc", LchScm, true, true, true)
        )
      )
    )

    specify("We can have a second, if fairly pointless, doc-jockey spec in the same Spec class")(
      ComputerIs(true)
    )
  }

  def expectedHtml =
    <html>
      <header>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="../../../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../../css/doc-jockey.css" rel="stylesheet" type="text/css"/>
      </header>
    <body>
      <div class="container">
        <div class="well well-lg">
          <h3>We can have a second, if fairly pointless, doc-jockey spec in the same Spec class</h3>
          <table class="table table-condensed table-bordered">
            <tr><th>Computer is</th><td class="success">on</td></tr>
          </table>
        </div>
        <div class="well well-lg">
          <h3>Trade clearing engine supports correct workflows and product types</h3>
          <table class="table table-condensed table-bordered">
            <tr><th>Computer is</th><td class="success">on</td></tr>
          </table>
          <table class="table table-condensed table-bordered">
            <tr><th>Supported clearing workflows</th> <td>LCH-FCM</td><td class="success">Manual, Netting</td></tr>
          </table>
          <table class="table table-condensed table-bordered">
            <thead>
              <tr><th>Product types supported</th></tr>
              <tr><td>Description</td><td>Clearing house</td><td>Vanilla</td><td>FRA</td><td>VNS</td></tr>
            </thead>
            <tbody>
              <tr><td>some desc</td><td>LCH-FCM</td><td class="success">✓</td><td class="success">-</td><td class="success">✓</td></tr>
              <tr><td>some other desc</td><td>LCH-SCM</td><td class="success">✓</td><td class="success">✓</td><td class="danger"><span class="expectedText">✓</span><span class="actualText">-</span></td></tr>
            </tbody>
          </table>
        </div>
      </div>
    </body>
    </html>
  }