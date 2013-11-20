package example.project.specs.sandbox.hole

import org.scalatest.MockJockeySpec
import example.project.fixture.SupportedWorkflows
import example.project.main._
import example.project.fixture.SupportedWorkflows

object Hider {
  class SandboxedExampleSpec extends MockJockeySpec {
    specify("Example sandboxed doc-jockey spec")(
      SupportedWorkflows(LchFcm, List(Manual))
    )
  }
}