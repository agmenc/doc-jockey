package doc.jockey.model

import scala.xml.NodeSeq
import scala.xml.RichNodeSeq._

trait TestNode {
  def cmd: JustACommand
  def renderTable: NodeSeq = children match {
    case Nil => <table><tr><td>{cmd.title}</td>{renderCommand}</tr></table>
    case some => <table><tr><td>{cmd.title}</td></tr><tr>{renderCommand}</tr>{children.map(c => <tr>{c.renderCommand}</tr>)}</table>
  }

  def renderCommand: NodeSeq = renderFrom.map(_.render).toNodeSeq
  def renderFrom: List[Result]
  def children: List[TestNode]
}

case class Before(cmd: JustACommand) extends TestNode {
  def execute: After = After(cmd, cmd.execute, children.map(_.execute))
  def renderFrom = cmd.expecteds
  def children: List[Before] = cmd.subCommands.map(Before)
}

case class After(cmd: JustACommand, results: List[Result], children: List[After]) extends TestNode {
  def renderFrom = results
  def summary = Summary(results ::: children.flatMap(_.results))
}