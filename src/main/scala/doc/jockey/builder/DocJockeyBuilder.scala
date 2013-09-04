package doc.jockey.builder

import doc.jockey.model.Command
import scala.collection.mutable.ListBuffer
import doc.jockey.engine.DocJockey
import org.scalatest.Assertions

/**
 *
 *  User editing ==> GUI <=3=> Canonical data model <=1=> Code
 *                                        |
 *                                        |=2=> Runnable Spec == database
 *
 */
trait DocJockeyBuilder extends Assertions {
  private val commands = ListBuffer[Command]()

  // TODO - CAS - 29/08/2013 - we want to extract systems in a type-safe way: system(MyClass.type) = instanceOfMyClass
  // def subSystem(testable: => Any) = commands += System(testable)

  def command(cmds: Command*) = commands ++= cmds
  def table(tableMaker: (Seq[List[String]]) => Table, heading: Seq[String], rows: List[String]*) { commands += tableMaker(rows) }
  def row(expecteds: String*): List[String] = expecteds.toList
  def header(expecteds: String*) = row(expecteds:_*)

  def summary {
    // Generate a tree structure, with pass/fail/exception
    assert(new DocJockey(commands).execute.status === Pass)
  }
}