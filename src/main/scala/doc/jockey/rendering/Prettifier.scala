package doc.jockey.rendering

import scala.xml._
import scala.Pimps._

object Prettifier {
  private def prettifier = new PrettyPrinter(1000, 4)

  implicit def render(ns: NodeSeq): String = tee(new StringBuilder())(sb => ns.map(prettifier.format(_, sb))).toString()
}