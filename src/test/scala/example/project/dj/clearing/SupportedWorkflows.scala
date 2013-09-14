package example.project.dj.clearing

import doc.jockey.model.Command
import example.project.main.{TradeClearingEngine, Workflow, ClearingHouse}

trait Cell[T] {
  def data: T
}

case class Setup[T](data: T) extends Cell[T]
case class Assert[T](data: T) extends Cell[T]

case class SupportedWorkflows(clearingHouse: ClearingHouse, workflows: List[Workflow]) extends Command {
  def execute = ??? // new TradeClearingEngine().supportedWorkflows(clearingHouse.data)
}