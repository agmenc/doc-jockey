package doc.jockey.model

import org.scalatest.WordSpec

class JustACommandSpec extends WordSpec {
  case class SomeCmd(x: Boolean) extends JustACommand with YesOrNo {
    def title = "Some command"
    def expecteds = Pass("Yes") :: Nil
    def actuals = x :: Nil
  }

  "Executing a command gives you a list of results" in {
    assert(SomeCmd(true).execute === List(Pass("Yes")))
  }

  "Failed commands have failure results" in {
    assert(SomeCmd(false).execute === List(Fail("Yes", "No")))
  }

  "A command can render code for itself" in {
    assert(SomeCmd(false).inCode === "SomeCmd(false)")
  }
}