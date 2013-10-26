package example.project.fixture

import doc.jockey.model._
import example.project.fixture.SupportedProductTypes._
import example.project.main._

case class SupportedProductTypes(tradeTypes: List[TradeType], subExpecteds: List[Expected]) extends JustACommand {
  def title = "Product types supported"
  def expecteds: List[Result] = List(Setup("Description"), Setup("Clearing house")) ++ tradeTypes.map(Setup(_))
  def actuals: List[String] = expecteds.map(_.expected)

  override def subCommands = subExpecteds.map(WrapZippyThing(_, tradeTypes))
}

object SupportedProductTypes {
  case class Expected(desc: String, clearingHouse: ClearingHouse, supporteds: Boolean*)
}

case class WrapZippyThing(expected: Expected, tradeTypes: List[TradeType]) extends JustACommand with TickOrDash {

  import expected._

  // TODO - CAS - 25/09/2013 - Fail clearly if the headers and expecteds are different lengths

  def title = "Table row"
  def expecteds: List[Result] = Setup(desc) :: Setup(clearingHouse) :: supporteds.toList.map(Pass(_))
  def actuals: List[String] = List[String](desc, clearingHouse) ++ clearingResults
  private def clearingResults: List[String] = tradeTypes.map(clear(_) == Clear)
  private def clear(tradeType: TradeType) = TradeClearingEngine.clearTrade(Trade(clearingHouse, tradeType, Money(Usd, Amount(10000000))))
}