package example.project.dj

import doc.jockey.model._
import example.project.dj.clearing.SupportedProductTypes

case object TradeWorkflow extends Mixin {
  def noLimits(): Command = new Command {}
  def autoAccept(): Command = new Command {}

  def supportedProductTypes(rows: List[String]*): Table = SupportedProductTypes(rows)
}