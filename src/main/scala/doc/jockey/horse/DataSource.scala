package doc.jockey.horse

// Trees, tables, lists
// Data has shapes, Concepts bind to shapes
// Data may come from different data sources, e.g. CSV, XML, HTML, case classes, etc
// Is data just a pimp for ordinary collection types?
trait DataSource[T] {
  def read: T
  def write(data: T): DataSource[T]
}

case class Static[T](data: T) extends DataSource[T] {
  override def read = data
  override def write(data: T) = Static(data)
}

// Mixins for remote datasources?
trait Protocol
trait Http extends Protocol