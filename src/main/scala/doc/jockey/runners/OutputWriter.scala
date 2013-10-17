package doc.jockey.runners

import scala.Pimps._
import scala.xml._
import doc.jockey.rendering.Prettifier._
import scala.reflect.io._
import doc.jockey.rendering.Html

class OutputWriter(callingSpec: Class[_]) {
  private val baseDir = Path(".") / "target" / "doc-jockey"
  private val packageDir = callingSpec.getPackage.getName replaceAll ("\\.", File.separator)
  private val fileName = callingSpec.getSimpleName + ".html"

  def write(ns: NodeSeq) { synchronized(file.appendAll(ns)) }
  def close() { file.appendAll(Html.footer) }

  private[runners] def file: File = tee((dir / fileName).toFile)(_.createFile())
  private[runners] def dir: Directory = tee((baseDir / packageDir).toDirectory)(_.createDirectory())

  file.writeAll(Html.header)
}