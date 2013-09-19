package example.project.main

object TradeClearingEngine {
  def clearTrade(trade: Trade): ClearingResult = (trade.clearingHouse, trade.tradeType) match {
    case (LchFcm, Fra) => Reject
    case (LchScm, Vns) => Reject
    case _ => Clear
  }

  def supportedWorkflows(clearingHouse: ClearingHouse): List[Workflow] = clearingHouse match {
    case LchScm => List(Manual)
    case LchFcm => List(Manual, Netting)
  }
}