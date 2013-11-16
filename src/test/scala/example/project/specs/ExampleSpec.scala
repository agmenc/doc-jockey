package example.project.specs

import example.project.fixture.SupportedWorkflows
import example.project.main._
import org.scalatest.DocJockeySpec

class ExampleSpec extends DocJockeySpec {
  specify("Example doc-jockey spec")(
    SupportedWorkflows(LchFcm, List(Manual, Netting))
  )
}