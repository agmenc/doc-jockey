package example.project.dj.clearing

import example.project.main.{TradeClearingEngine, Workflow, ClearingHouse}

case class SupportedWorkflows(clearingHouse: ClearingHouse, workflows: List[Workflow])