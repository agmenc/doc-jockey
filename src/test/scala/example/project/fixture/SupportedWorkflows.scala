package example.project.fixture

import doc.jockey.model._
import example.project.fixture._
import example.project.main.{Workflow, ClearingHouse}

case class SupportedWorkflows(clearingHouse: ClearingHouse, workflows: List[Workflow]) extends JustACommand {
  def execute = ??? //Setup(clearingHouse)
  def title = "Supported clearing workflows"
  def expecteds = List(clearingHouse, workflows)
}