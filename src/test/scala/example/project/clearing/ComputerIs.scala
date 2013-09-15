package example.project.dj.clearing

import doc.jockey.model.{Fail, Pass, Implicits, JustACommand}
import example.project.clearing.Computer

case class ComputerIs(expected: Boolean) extends JustACommand {
  import Implicits._

  def execute = List(
    // TODO - CAS - 14/09/2013 - Map from types to Strings, maybe with a shapeless polymorphic map or bi-map
    if (expected == Computer.isOn) Pass(expected) else Fail(expected, Computer.isOn)
  )

  def title = "Computer is"
  def expecteds = List(expected)
}