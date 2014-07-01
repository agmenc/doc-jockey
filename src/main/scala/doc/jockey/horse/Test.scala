package doc.jockey.horse

import Test._

// Tests are also data
// Aggregates disparate data sources, finds the appropriate Concepts and sends the results to a display
case class Test[DC<:DisplayContext](title: String, testDisplay: DC, actionsAndAssertions: Binding[_, DC]*) {
  // We could diff pre- and post- Bindings, and only show the diffs .... allows us to group all failures in one short output
  lazy val execute: Seq[(Binding[_, DC], Binding[_, DC])] = Nil
}

object Test {
  // A binding needs no context to be executable - data + concept = executable
  case class Binding[T, DC<:DisplayContext](data: T, concept: Concept[T], display: Display[T, DC])
}