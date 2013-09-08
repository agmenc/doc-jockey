package example.project.main

trait Workflow

object Workflow {
  private lazy val workflows = List(Manual, Auto, Netting)

  implicit def convert(name: String): Workflow = workflows.find(_.toString == name).get
}

case object Manual extends Workflow
case object Auto extends Workflow
case object Netting extends Workflow

