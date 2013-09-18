package example.project.fixture

import doc.jockey.model.Before
import doc.jockey.model._
import org.scalatest.WordSpec

class ComputerIsSpec extends WordSpec {
  // Thingys
  "Executing a Before gives you an After" in {
    val aCmd = ComputerIs(true)
    assert(Before(aCmd).execute === After(aCmd, List(Pass("on"))))
  }

  "Befores can render themselves" in {
    assert(Before(ComputerIs(true)).render === <table><tr><td>Computer is</td><td>on</td></tr></table>)
  }

  "Afters can render themselves" in {
    assert(After(ComputerIs(true), List(Fail("off", "on"))).render === <table><tr><td>Computer is</td><td><span class="failText">off</span>on</td></tr></table>)
  }

  "Afters have Summaries" in {
    assert(After(ComputerIs(true), List(Fail("off", "on"))).summary === Summary(0, 1))
  }

  // Commands
  "Executing a command gives you a list of results" in {
    assert(ComputerIs(true).execute === List(Pass("on")))
  }

  "Failed commands have failure results" in {
    assert(ComputerIs(false).execute === List(Fail("off", "on")))
  }

  "A command can render code for itself" in {
    assert(ComputerIs(false).inCode === "ComputerIs(false)")
  }
}