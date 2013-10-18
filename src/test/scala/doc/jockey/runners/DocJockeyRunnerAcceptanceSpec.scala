package doc.jockey.runners

import doc.jockey.model.Summary
import example.project.fixture.SupportedTradeTypes.Expected
import example.project.fixture._
import example.project.main._
import org.scalatest.WordSpec
import doc.jockey.rendering.HtmlAssertions

class DocJockeyRunnerAcceptanceSpec extends WordSpec with HtmlAssertions {
  val testModel = List(ComputerIs(true))

  "We can get an overall Summary of the test run" in {
    val runner = DocJockeyRunner("Can we get a summary?", testModel)

    assert(runner.summary.isAPass === true)
    assert(runner.summary === Summary(1, 0))
  }

  "The runner outputs some HTML" in {
    val expected =
      <h2>Can we get some HTML?</h2> ++
      <table>
        <tr>
          <td>Computer is</td>
          <td>on</td>
        </tr>
      </table>

    val runner = DocJockeyRunner("Can we get some HTML?", testModel)

    assertEqual(runner.output, expected)
  }
}