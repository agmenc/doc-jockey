package doc.jockey.rendering

import doc.jockey.model._
import org.scalatest.WordSpec

class TreePainterSpec extends WordSpec with TableAware {
  val title = "Single row command"

  case class SingleRowCmd(testPasses: Boolean) extends JustACommand with YesOrNo {
    def title = "Single row command"
    def expecteds = Setup("Does this thing work?") :: Pass(true) :: Nil
    def actuals = List("Does this thing work?", testPasses)
  }

  "Befores render from expecteds, while Afters render from actual Results" in {
    val cmd = SingleRowCmd(true)
    val before = Before(cmd)
    val after = before.execute

    assert(before.renderFrom === before.cmd.expecteds)
    assert(after.renderFrom === after.results)
  }

  "We can paint trees of Befores and Afters" in {
    val test = List(SingleRowCmd(true), SingleRowCmd(false), SingleRowCmd(true)).map(Before(_).execute)

    val result = new TreePainter().paint(test)

    assert(result.tables(1).cells === title :: "Does this thing work?" :: "YesNo" :: Nil)
  }
}