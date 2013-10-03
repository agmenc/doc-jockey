package doc.jockey.rendering

import doc.jockey.model._
import org.scalatest.WordSpec

class TreePainterSpec extends WordSpec with TableAware {
  val title = "Single row command"

  case class SingleRowCmd(works: Boolean) extends JustACommand with YesOrNo {
    def title = "Single row command"
    def expecteds = Setup("Does this thing work?") :: Pass(works) :: Nil
    def actuals = List("Does this thing work?", works)
  }

  "We can paint a single Before" in {
    val cmd = SingleRowCmd(true)
    val test = Before(cmd) :: Nil

    val result = new TreePainter().paint(test)

    assert(result.tables.head.cells === title :: "Does this thing work?" :: "Yes" :: Nil)
  }

//  "We can paint a single After" in {
//    val cmd = SingleRowCmd(false)
//    val test = (Before(cmd) :: Nil).map(_.execute)
//
//    val result = new TreePainter().paint(test)
//
//    assert(result.tables.head.cells === title :: "Does this thing work?" :: "FAIL: actual: No, expected: Yes" :: Nil)
//  }

  "We can paint Before trees" in {
    val test = List(SingleRowCmd(true), SingleRowCmd(false), SingleRowCmd(true)).map(Before)

    val result = new TreePainter().paint(test)

    assert(result.tables(1).cells === title :: "Does this thing work?" :: "No" :: Nil)
  }
}