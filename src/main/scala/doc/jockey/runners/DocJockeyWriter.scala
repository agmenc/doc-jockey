package doc.jockey.runners

import scala.Pimps._
import scala.xml._
import doc.jockey.rendering.Prettifier._
import scala.reflect.io._
import doc.jockey.rendering.Html

object DocJockeyWriter {
  def write(ns: NodeSeq) { file.writeAll(Html(ns)) }
  def file: File = tee((dir / "output.html").toFile)(_.createFile())
  def dir: Directory = tee((Path(".") / "target" / "doc-jockey").toDirectory)(_.createDirectory())
}