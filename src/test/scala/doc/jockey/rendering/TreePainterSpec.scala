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

  "We can paint a single Before" in {
    val cmd = SingleRowCmd(true)
    val test = Before(cmd) :: Nil

    val result = new TreePainter().paint(test)

    assert(result.tables.head.cells === title :: "Does this thing work?" :: "Yes" :: Nil)
  }

  "We can paint a single After" in {
    val cmd = SingleRowCmd(false)
    val test = (Before(cmd) :: Nil).map(_.execute)

    val result = new TreePainter().paint(test)

    assert(result.tables.head.cells === title :: "Does this thing work?" :: "YesNo" :: Nil)
  }

  "We can paint Before trees" in {
    val test = List(SingleRowCmd(true), SingleRowCmd(false), SingleRowCmd(true)).map(Before)

    val result = new TreePainter().paint(test)

    result.tables.foreach{table =>
      assert(table.cells === title :: "Does this thing work?" :: "Yes" :: Nil)
    }
  }

  "We can paint After trees" in {
    val test = List(SingleRowCmd(true), SingleRowCmd(false), SingleRowCmd(true)).map(Before(_).execute)

    val result = new TreePainter().paint(test)

    assert(result.tables(1).cells === title :: "Does this thing work?" :: "YesNo" :: Nil)
  }
}