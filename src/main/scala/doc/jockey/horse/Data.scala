package doc.jockey.horse

// Trees, tables, lists
// Data has shapes, Concepts bind to shapes
// Data must have a pre-executed and a post-executed format. Post-executed might be a transformation of the original
// Data may come from different data sources, e.g. CSV, XML, HTML, case classes, etc
trait Data {
  def map(tx: Elem => Elem)
}

trait Elem