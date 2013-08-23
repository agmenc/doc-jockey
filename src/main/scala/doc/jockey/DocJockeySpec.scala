package doc.jockey

import doc.jockey.model.{Table, Command}
import scala.collection.mutable.ListBuffer

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
trait DocJockeySpec {
  private val commands = ListBuffer[Command]()
  def command(cmds: Command*) = commands ++= cmds
  def table(table: Table) = commands += table
  def summary = new DocJockey.ex
}