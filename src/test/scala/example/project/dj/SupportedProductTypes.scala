package example.project.dj

import example.project.main.ClearingHouse
import doc.jockey.model.{Settings, TableRow, Table}

case class SupportedProductTypes(settings: Option[Settings], rows: TableRow*) extends Table

object SupportedProductTypes {
  def row(desc: String, clearingHouse: ClearingHouse, productTypeSupported: Boolean*): TableRow = new TableRow {}
}