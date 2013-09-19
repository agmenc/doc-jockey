package example.project.fixture

import doc.jockey.model._
import example.project.fixture._
import example.project.main._
import doc.jockey.model.Setup

case class SupportedWorkflows(clearingHouse: ClearingHouse, workflows: List[Workflow]) extends JustACommand {
  def title = "Supported clearing workflows"
  def actuals: List[String] = clearingHouse :: supportedWorkflows :: Nil
  def expecteds: List[Result] = List(Setup(clearingHouse), Pass(workflows))

  private def supportedWorkflows: String = TradeClearingEngine.supportedWorkflows(clearingHouse)
}