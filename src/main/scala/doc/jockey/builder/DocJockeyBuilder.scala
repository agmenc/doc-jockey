package doc.jockey.builder

import doc.jockey.model._
import scala.collection.mutable.ListBuffer
import doc.jockey.engine.DocJockey
import org.scalatest.Assertions

trait DocJockeyBuilder extends Assertions {
  private var root: Tree[Command] = Tree.empty

  // TODO - CAS - 29/08/2013 - we want to extract systems in a type-safe way: system(MyClass.type) = instanceOfMyClass
  // def subSystem(testable: => Any) = commands += System(testable)

  def command(cmd: Command) { root = root.append(Leaf(cmd)) }

  // TODO - CAS - 06/09/2013 - Build a tree, append it to the parent
  def table(tableMaker: (Seq[List[String]]) => Table, heading: Seq[String], rows: List[String]*) {
    root = root.append(Branch())
  }

  def row(expecteds: String*): List[String] = expecteds.toList
  def header(expecteds: String*) = row(expecteds:_*)

  def summary {
    // Generate a tree structure, with pass/fail/exception
    assert(new DocJockey(commands).execute.status === Pass)
  }
}