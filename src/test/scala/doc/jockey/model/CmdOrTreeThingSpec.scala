package doc.jockey.model

import org.scalatest.WordSpec
import example.project.fixture.ComputerIs

class CmdOrTreeThingSpec extends WordSpec {
  "Executing a Before gives you an After" in {
    val aCmd = ComputerIs(true)
    assert(Before(aCmd).execute === After(aCmd, List(Pass("on")), Nil))
  }

  "Befores can render themselves" in {
    assert(Before(ComputerIs(true)).render === <table><tr><td>Computer is</td><td>on</td></tr></table>)
  }

  "Afters can render themselves" in {
    assert(After(ComputerIs(true), List(Fail("off", "on")), Nil).render === <table><tr><td>Computer is</td><td><span class="failText">off</span>on</td></tr></table>)
  }

  "Afters have Summaries" in {
    assert(After(ComputerIs(true), List(Fail("off", "on")), Nil).summary === Summary(0, 1))
  }
}