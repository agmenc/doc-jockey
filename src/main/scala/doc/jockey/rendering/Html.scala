package doc.jockey.rendering

import doc.jockey.rendering.Prettifier._
import doc.jockey.files.FileOps
import scala.reflect.io.File

class Html(caller: Class[_]) {
  import FileOps._

  private def html = <html>{headers}<body>@STUFF@</body></html>
  private def headers =
    <header>
      <link href='../../../doc-jockey.css' rel='stylesheet' type='text/css'/>
    </header>

  val Seq(header, footer) = html.split("@STUFF@").toSeq
  def reversePackage = caller.packageDir.split(File.separator).map(s => "..").mkString("/")
}

object Html {
  def apply(caller: Class[_]): Html = new Html(caller)
}