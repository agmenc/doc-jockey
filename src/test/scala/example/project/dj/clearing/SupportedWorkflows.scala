package example.project.dj.clearing

import doc.jockey.model.Command
import example.project.main.{Workflow, ClearingHouse}

case class SupportedWorkflows(clearingHouse: ClearingHouse, workflows: List[Workflow]) extends Command {
  def execute = ???
}