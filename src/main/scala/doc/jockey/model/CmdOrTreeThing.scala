package doc.jockey.model

import scala.xml.NodeSeq

trait CmdOrTreeThing {
  def cmd: JustACommand
  def render = <table><tr><td>{cmd.title}</td>{cells}</tr></table>
  def cells = renderFrom.foldLeft(NodeSeq.Empty)((ns, r) => ns ++ r.render)
  def renderFrom: List[Result]
  def children: List[CmdOrTreeThing]
}

case class Before(cmd: JustACommand) extends CmdOrTreeThing {
  def execute: After = After(cmd, cmd.execute, children.map(_.execute))
  def renderFrom = cmd.expecteds
  def children: List[Before] = cmd.subCommands.map(Before)
}

case class After(cmd: JustACommand, results: List[Result], children: List[After]) extends CmdOrTreeThing {
  def renderFrom = results
  def summary = Summary(results)
}