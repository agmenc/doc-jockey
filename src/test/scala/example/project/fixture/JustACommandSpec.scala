package example.project.fixture

import doc.jockey.model.Before
import doc.jockey.model._
import org.scalatest.WordSpec

class JustACommandSpec extends WordSpec {
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