package example.project.dj.clearing

import doc.jockey.model.Summary
import scala.xml.NodeSeq
import org.scalatest.WordSpec

trait CmdOrTreeThing {
  def cmd: JustACommand
  def render = <table><tr><td>{cmd.title}</td>{cells}</tr></table>
  def cells: NodeSeq
  def inCode: String = ???
}

case class Before(cmd: JustACommand) extends CmdOrTreeThing {
  def execute: After = After(cmd, cmd.execute)
  def cells = cmd.expecteds.foldLeft(NodeSeq.Empty)((ns, e) => ns ++ <td>{e}</td>)
}

case class After(cmd: JustACommand, results: List[Result]) extends CmdOrTreeThing {
  def summary: Option[Summary] = None
  def cells = results.foldLeft(NodeSeq.Empty)((ns, r) => ns ++ r.render)
}

trait JustACommand {
  def execute: List[Result]
  def title: String
  def expecteds: List[String]
}

trait Result {
  def render: NodeSeq
}

case class Pass(expected: String) extends Result {
  def render = <td>{expected}</td>
}

case class Fail(expected: String, actual: String) extends Result {
  def render = <td><span class="failText">{expected}</span>{actual}</td>
}

case class ComputerIs(expected: Boolean) extends JustACommand {
  import Implicits._

  def execute = List(
    // TODO - CAS - 14/09/2013 - Map from types to Strings, maybe with a shapeless polymorphic map or bi-map
    if (expected == Computer.isOn) Pass(expected) else Fail(expected, Computer.isOn)
  )

  def title = "Computer is"
  def expecteds = List(expected)
}

object Implicits {
  implicit def onOrOff(bool: Boolean): String = if (bool) "on" else "off"
}

object Computer {
  def isOn = true
}

class AssertComputerIsSpec extends WordSpec {
  // Thingys
  "Executing a Before gives you an After" in {
    val aCmd = ComputerIs(true)
    assert(Before(aCmd).execute === After(aCmd, List(Pass("on"))))
  }

  "Befores can render themselves" in {
    assert(Before(ComputerIs(true)).render === <table><tr><td>Computer is</td><td>on</td></tr></table>)
  }

  "Afters can render themselves" in {
    assert(After(ComputerIs(true), List(Fail("off", "on"))).render === <table><tr><td>Computer is</td><td><span class="failText">off</span>on</td></tr></table>)
  }

  // Commands
  "Executing a command gives you a list of results" in {
    assert(ComputerIs(true).execute === List(Pass("on")))
  }

  "Failed commands have failure results" in {
    assert(ComputerIs(false).execute === List(Fail("off", "on")))
  }
}