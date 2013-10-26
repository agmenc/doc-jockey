package doc.jockey.model

import org.scalatest.WordSpec
import example.project.fixture.ComputerIs
import doc.jockey.rendering.HtmlAssertions

class TestNodeBehaviourSpec extends WordSpec with HtmlAssertions {
  "Executing a Before gives you an After" in {
    val aCmd = ComputerIs(true)
    assert(Before(aCmd).execute === After(aCmd, List(Pass("on")), Nil))
  }

  "Afters have Summaries" in {
    assert(After(ComputerIs(true), List(Fail("off", "on")), Nil).summary === Summary(0, 1))
  }
}