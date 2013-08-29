package example.project.main

class TradeClearingEngine {
  def clearTrade(trade: Trade): ClearingResult = (trade.clearingHouse, trade.tradeType) match {
    case (LchFcm, Fra) => Reject
    case (LchScm, Vns) => Reject
    case _ => Clear
  }
}