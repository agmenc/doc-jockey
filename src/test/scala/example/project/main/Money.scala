package example.project.main

case class Money(currency: Currency, amount: Amount)

trait Currency
case object Usd extends Currency

case class Amount(number: BigDecimal)
