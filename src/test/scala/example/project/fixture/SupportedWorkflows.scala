package example.project.fixture

import doc.jockey.model._
import example.project.fixture._
import example.project.main._

case class SupportedWorkflows(clearingHouse: ClearingHouse, workflows: List[Workflow]) extends JustACommand {
  def title = "Supported clearing workflows"
  def expecteds: List[Result] = Setup(clearingHouse) :: Pass(workflows) :: Nil
  def actuals: List[String] = clearingHouse :: supportedWorkflows :: Nil

  private def supportedWorkflows: String = TradeClearingEngine.supportedWorkflows(clearingHouse)
}