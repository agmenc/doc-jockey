package example.project.main

trait ClearingHouseImplicits {
  implicit def stringToClearingHouse(name: String): ClearingHouse = name match {
    case "LCH-FCM" => LchFcm
    case "LCH-SCM" => LchScm
  }

  implicit def clearingHouseToString(ch: ClearingHouse): String = ch match {
    case LchFcm => "LCH-FCM"
    case LchScm => "LCH-SCM"
  }

  implicit def stringToWorkflow(name: String): Workflow = Workflow.workflows.find(_.toString == name).get
  implicit def workflowToString(wf: Workflow): String = wf.toString

  implicit def stringToTradeType(name: String): TradeType = TradeType.tradeTypes.find(_.toString == name).get
  implicit def tradeTypeToString(tt: TradeType): String = tt.toString
}