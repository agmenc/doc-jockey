package doc.jockey.runners

import doc.jockey.model.Summary
import doc.jockey.rendering.HtmlAssertions
import example.project.fixture._
import org.scalatest.WordSpec

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
        <table class="table table-condensed table-bordered">
        <tr>
          <th>Computer is</th>
          <td>on</td>
        </tr>
      </table>

    val runner = DocJockeyRunner("Can we get some HTML?", testModel)

    assertEqual(runner.output, expected, "X")
  }
}