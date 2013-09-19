package doc.jockey.model

import scala.xml.NodeSeq

trait CmdOrTreeThing {
  def cmd: JustACommand
  def render = <table><tr><td>{cmd.title}</td>{cells}</tr></table>
  def cells: NodeSeq
}

case class Before(cmd: JustACommand) extends CmdOrTreeThing {
  def execute: After = After(cmd, cmd.execute)
  def cells = cmd.expecteds.foldLeft(NodeSeq.Empty)((ns, e) => ns ++ <td>{e.expected}</td>)
}

case class After(cmd: JustACommand, results: List[Result]) extends CmdOrTreeThing {
  def cells = results.foldLeft(NodeSeq.Empty)((ns, r) => ns ++ r.render)
  def summary = Summary(results)
}