package doc.jockey.runners

import doc.jockey.model.Summary
import example.project.fixture.SupportedTradeTypes.Expected
import example.project.fixture._
import example.project.main._
import org.scalatest.WordSpec
import doc.jockey.rendering.HtmlAssertions

class DocJockeyRunnerAcceptanceSpec extends WordSpec with HtmlAssertions {
  val testModel = List(
    ComputerIs(true),
    SupportedWorkflows(LchFcm, List(Manual, Netting)),
    SupportedTradeTypes(
      List(Vanilla, Fra, Vns),
      List(
        Expected("some desc", LchFcm, true, false, true),
        Expected("some other desc", LchScm, true, true, false)
      )))

  val expectedHtml =
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

  // |Description     |Clearing House|Vanilla |FRA | VNS |
  // |some desc       |LCH-FCM       |   ✓   |  -  |  ✓  |
  // |some other desc |LCH-SCM       |   ✓   |  ✓  |  -  |


  "We can get an overall Summary of the test run" in {
    val runner = DocJockeyRunner(testModel)

    assert(runner.summary.isAPass === true)
    assert(runner.summary === Summary(8, 0))
  }

  "The runner outputs some HTML" in {
    val runner = DocJockeyRunner(testModel)

    assertEqual(runner.output, expectedHtml)
  }
}