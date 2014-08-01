package doc.jockey.horse.pages

import scala.xml.NodeSeq

trait Page {
  def render: NodeSeq

  def template =
    <html>
      <head lang="en">
        <meta charset="UTF-8"></meta>
        <link rel="resources/css/doc-jockey.css"></link>
        <title></title>
      </head>
      <body>
        {render}
      </body>
    </html>

  def byteses = template.toString()

}

object Index extends Page {
  val render = <div>Index</div>
}