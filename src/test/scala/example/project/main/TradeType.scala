package example.project.main

trait TradeType

object TradeType {
  def tradeTypes = List(Vanilla, Fra, Vns)
}

case object Vanilla extends TradeType
case object Fra extends TradeType
case object Vns extends TradeType