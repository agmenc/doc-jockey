package example.project.dj.clearing

import doc.jockey.model.Summary
import scala.xml.NodeSeq

trait CmdOrTreeThing {
  def execute: CmdOrTreeThing
  def render: NodeSeq
  def summary: Option[Summary] = None
}

case class AssertComputerIs(expected: String) extends CmdOrTreeThing {
  def actual = "On" // or hit the thing to be tested ...

  def render = <poo></poo>
  def execute = Summary(1,0,0)
}