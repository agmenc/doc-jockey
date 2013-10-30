package doc.jockey.runners

import doc.jockey.files.FileOps
import doc.jockey.rendering.Html
import doc.jockey.rendering.Prettifier._
import scala.xml._

class OutputWriter(callingSpec: Class[_]) extends FileOps(callingSpec) {
  def write(ns: NodeSeq): Unit = file.appendAll(ns)

  def close() {
    file.appendAll(Html.footer)
    file.writeAll(XML.loadString(file.slurp()))
  }

  file.writeAll(Html.header)
}