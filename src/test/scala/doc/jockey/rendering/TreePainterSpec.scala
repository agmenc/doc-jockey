package doc.jockey.rendering

import doc.jockey.model._
import org.scalatest.WordSpec
import scala.xml._

class TreePainterSpec extends WordSpec {
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

    val cells = (result \\ "tr" \ "td").map(_.text)
    assert(cells === title :: "Does this thing work?" :: "Yes" :: Nil)
  }

  "We can paint Before trees" in {
    val test = List(SingleRowCmd(true), SingleRowCmd(false), SingleRowCmd(true)).map(Before)

    val result: NodeSeq = new TreePainter().paint(test)

    val middleTable = (result \\ "table").tail.head
    val cells = (middleTable \ "tr" \ "td").map(_.text)
    assert(cells === title :: "Does this thing work?" :: "No" :: Nil)
  }
}