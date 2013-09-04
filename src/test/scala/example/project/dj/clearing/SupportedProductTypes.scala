package example.project.dj.clearing

import doc.jockey.model.{Command}
import doc.jockey.builder.{Table, Summary}

case class SupportedProductTypes(rows: Seq[List[String]]) extends Table {
  def execute = {
    val header :: data = rows.toList

    // Look up header type adapters in some map

    data
      .map(rowToCommand)
      .map(_.execute)
      .reduce(_ + _)
  }

  def rowToCommand(row: List[String]): Command = new Command{ def execute = Summary(0, 1, 0) }
}