package doc.jockey.model

import scala.xml.NodeSeq

trait Result {
  def expected: String
  def render: NodeSeq
}

case class Setup(expected: String) extends Result {
  def render = <td>{expected}</td>
}

case class Pass(expected: String) extends Result {
  def render = <td>{expected}</td>
}

case class Fail(expected: String, actual: String) extends Result {
  def render = <td class="danger"><span class="failText">{expected}</span>{actual}</td>
}