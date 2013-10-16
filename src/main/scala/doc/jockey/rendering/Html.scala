package doc.jockey.rendering

import doc.jockey.rendering.Prettifier._

object Html {
  val html = <html><header></header><body>@STUFF@</body></html>
  val Seq(header, footer) = html.split("@STUFF@").toSeq
}