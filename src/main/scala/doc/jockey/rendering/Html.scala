package doc.jockey.rendering

import doc.jockey.files.FileOps
import scala.reflect.io.File

class Html(caller: Class[_]) {
  import FileOps._

  def header =
    <header>
      <meta charset="UTF-8"/>
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <link href={pathTo("css/bootstrap.min.css")} rel="stylesheet" type="text/css"/>
      <link href={pathTo("css/doc-jockey.css")} rel="stylesheet" type="text/css"/>
    </header>

  val Seq(top, middle, bottom) = Seq("<!DOCTYPE html>\n<html>", "<body><div class=\"container\">", "</div></body></html>")

  private def pathTo(resource: String): String = reversePackage + File.separator + resource
  private def reversePackage = caller.packageDir.split(File.separator).map(s => "..").mkString("/")
}

object Html {
  def apply(caller: Class[_]): Html = new Html(caller)
}