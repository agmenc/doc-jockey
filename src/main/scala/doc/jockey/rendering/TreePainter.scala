package doc.jockey.rendering

import doc.jockey.model.Before
import scala.xml.NodeSeq

class TreePainter {
  def paint(befores: List[Before]): NodeSeq =
    <html>
      <body>{befores.map(_.render).foldLeft(NodeSeq.Empty)(_ ++ _)}</body>
    </html>
}