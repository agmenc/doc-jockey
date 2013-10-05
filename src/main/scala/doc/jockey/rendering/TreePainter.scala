package doc.jockey.rendering

import doc.jockey.model._
import scala.xml.NodeSeq

class TreePainter {
  def paint(cmdOrTreeThings: List[CmdOrTreeThing]): NodeSeq =
    <html>
      <body>{cmdOrTreeThings.map(_.render).foldLeft(NodeSeq.Empty)(_ ++ _)}</body>
    </html>
}