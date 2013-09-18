package doc.jockey.model

import scala.xml.NodeSeq

trait Result {
  def render: NodeSeq
}

case class Setup(data: String) extends Result {
  def render = <td>{data}</td>
}

case class Pass(expected: String) extends Result {
  def render = <td>{expected}</td>
}

case class Fail(expected: String, actual: String) extends Result {
  def render = <td><span class="failText">{expected}</span>{actual}</td>
}