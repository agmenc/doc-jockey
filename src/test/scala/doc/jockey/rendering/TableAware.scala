package doc.jockey.rendering

import scala.xml.NodeSeq

trait TableAware {
  implicit class Page(ns: NodeSeq) {
    def tables: List[Table] = (ns \\ "table").map(new Table(_)).toList
  }

  implicit class Table(ns: NodeSeq) {
    def rows: List[Row] = (ns \\ "tr").map(new Row(_)).toList
    def cells: List[String] = (ns \\ "td").map(_.text).toList
  }

  implicit class Row(ns: NodeSeq) {
    def cells: List[String] = (ns \\ "td").map(_.text).toList
  }
}