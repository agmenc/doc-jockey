package example.project.fixture

import doc.jockey.model._

case class ComputerIs(expected: Boolean) extends JustACommand {

  def execute = List(
    // TODO - CAS - 14/09/2013 - Map from types to Strings, maybe with a shapeless polymorphic map or bi-map
    if (expected == Computer.isOn) Pass(expected) else Fail(expected, Computer.isOn)
  )

  def title = "Computer is"
  def expecteds = List(expected)
}