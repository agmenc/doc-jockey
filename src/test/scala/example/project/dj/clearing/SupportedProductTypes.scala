package example.project.dj.clearing

import doc.jockey.model.{Summary, Command}
import doc.jockey.builder.{Table}

case class SupportedProductTypes(headers: List[String], rows: List[String]*) extends Table {
  def execute = {
    val header :: data = rows.toList

    // Look up header type adapters in some map

    // TODO - CAS - 04/09/2013 - Reduce is not safe on an empty Seq
    data
      .map(rowToCommand)
      .map(_.execute)
      .reduce(_ + _)
  }

  def rowToCommand(row: List[String]): Command = new Command{ def execute = Summary(0, 1, 0) }
}