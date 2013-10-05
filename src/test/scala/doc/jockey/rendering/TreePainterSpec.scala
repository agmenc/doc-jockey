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

  "We can paint the tree of an executed test" in {
    val test = List(SingleRowCmd(true), SingleRowCmd(false), SingleRowCmd(true)).map(Before(_).execute)

    val result = new TreePainter().paint(test)

    assert(result.tables(1).cells === title :: "Does this thing work?" :: "YesNo" :: Nil)
  }
}