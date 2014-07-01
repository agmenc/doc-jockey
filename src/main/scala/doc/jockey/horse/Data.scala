package doc.jockey.horse

// Trees, tables, lists
// Data has shapes, Concepts bind to shapes
// Data must have a pre-executed and a post-executed format. Post-executed might be a transformation of the original
// Data may come from different data sources, e.g. CSV, XML, HTML, case classes, etc
// Is data just a pimp for ordinary collection types?
// Just use structural typing to use map() from whatever?
trait Data {
  def map(tx: Elem => Elem)
}

trait Protocol
trait Http extends Protocol



trait Elem

