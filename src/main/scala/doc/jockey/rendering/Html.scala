package doc.jockey.rendering

import scala.xml.NodeSeq

object Html {
  def apply(contents: NodeSeq): NodeSeq = <html><body>{contents}</body></html>
}