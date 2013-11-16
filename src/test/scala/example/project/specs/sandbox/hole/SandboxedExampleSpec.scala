package example.project.specs.sandbox.hole

import example.project.fixture.SupportedWorkflows
import example.project.main._
import org.scalatest._

class SandboxedExampleSpec extends MockJockeySpec {
  specify("Example sandboxed doc-jockey spec")(
    SupportedWorkflows(LchFcm, List(Manual))
  )
}