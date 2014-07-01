package example.project.specs

import example.project.fixture.SupportedWorkflows
import example.project.main._
import org.scalatest.DocJockeySpec

class ExampleSpec extends DocJockeySpec {

  // Instead of calling an action, we should build a data structure, which can be accessed for execution or editing

  // Registering specs from the data structure should be the job of the parent class (DocJockeySpec), perhaps using BeforeAll

  specify("Example doc-jockey spec")(
    SupportedWorkflows(LchFcm, List(Manual, Netting))
  )
}
