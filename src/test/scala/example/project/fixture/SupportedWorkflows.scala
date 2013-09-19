package example.project.fixture

import doc.jockey.model._
import example.project.fixture._
import example.project.main._
import doc.jockey.model.Setup

case class SupportedWorkflows(clearingHouse: ClearingHouse, workflows: List[Workflow]) extends JustACommand {
  def execute = compare(expectedsAlsos zip actuals)

  def compare(expectedsAndActuals: List[(Result, String)]) = expectedsAndActuals.map(_ match {
    case (exR, ac) if exR.expected == ac => exR
    case (exR, ac) => Fail(exR.expected, ac)
  })

  def title = "Supported clearing workflows"
  def expecteds = List(clearingHouse, workflows)
  def actuals: List[String] = clearingHouse :: supportedWorkflows :: Nil

  def expectedsAlsos: List[Result] = List(Setup(clearingHouse), Pass(workflows))

  private def supportedWorkflows: String = TradeClearingEngine.supportedWorkflows(clearingHouse)
}