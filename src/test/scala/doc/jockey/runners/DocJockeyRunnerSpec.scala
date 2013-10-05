package doc.jockey.runners

import doc.jockey.model.Summary
import example.project.fixture.ComputerIs
import example.project.fixture.SupportedWorkflows
import example.project.main._
import org.scalatest.WordSpec

class DocJockeyRunnerSpec extends WordSpec {
  val testModel = List(
    ComputerIs(false),
    SupportedWorkflows(LchScm, List(Manual, Netting)),
    SupportedWorkflows(LchFcm, List(Manual, Netting))
  )

  "We can get an overall Summary of the test run" in {
    val runner = DocJockeyRunner(testModel)

    assert(runner.summary.isAPass === false)
    assert(runner.summary === Summary(1, 2))
  }

  "The runner outputs some HTML" in {
    val runner = DocJockeyRunner(testModel)
    fail()
  }
}