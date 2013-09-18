package example.project.main

trait Workflow

object Workflow {
  lazy val workflows = List(Manual, Auto, Netting)
}

case object Manual extends Workflow
case object Auto extends Workflow
case object Netting extends Workflow

