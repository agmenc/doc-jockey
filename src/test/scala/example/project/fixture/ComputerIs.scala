package example.project.fixture

import doc.jockey.model._

case class ComputerIs(expected: Boolean) extends JustACommand with OnOrOff {
  def title = "Computer is"
  def expecteds = List(Pass(expected))
  def actuals = List(Computer.isOn)
}