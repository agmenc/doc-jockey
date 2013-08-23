package example.project.dj

import doc.jockey.model._

case object TradeWorkflow extends Mixin {
  def noLimits(): Command = new Command {}
  def autoAccept(): Command = new Command {}

  def supportedProductTypes(settings: Option[Settings], rows: TableRow*): Table = SupportedProductTypes(settings, rows:_*)
}