package doc.jockey

import doc.jockey.model.{Summary, Table, Command}
import scala.collection.mutable.ListBuffer
import doc.jockey.engine.DocJockey
import org.scalatest.Suite

/**
 *
 *  User editing ==> GUI <=3=> Canonical data model <=1=> Code
 *                                        |
 *                                        |=2=> Runnable Spec
 *
 *  (1) Convert from code to model
 *  (2) Execute as ScalaTest
 *  (3) Generate GUI output
 */
// TODO - CAS - 28/08/2013 - Extend Suite and provide a call like registerTestToRun() in WordSpec, which is called by "in"
trait DocJockeySpec {
  private val commands = ListBuffer[Command]()

  // TODO - CAS - 29/08/2013 - we want to extract systems in a type-safe way: system(MyClass.type) = instanceOfMyClass
//  def subSystem(testable: => Any) = commands += System(testable)
  def command(cmds: Command*) = commands ++= cmds
//  def commands(cmds: Command*) = commands ++= cmds
  def table(tableMaker: (Seq[List[String]]) => Table, heading: Seq[String], rows: List[String]*) { commands += tableMaker(rows) }
  def row(expecteds: String*): List[String] = expecteds.toList
  def header(expecteds: String*) = row(expecteds:_*)
  def summary = new DocJockey(commands).execute
}