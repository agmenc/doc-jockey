package doc.jockey.horse

import Test._

// Tests are also data
// Aggregates disparate data sources, finds the appropriate Concepts and sends the results to a display
// We could diff pre- and post- Bindings, and only show the diffs .... allows us to group all failures in one short output
case class Test[DC<:DisplayContext](title: String, actionsAndAssertions: Binding[_, DC]*) {
  def execute = Test(title, processBindings:_*)

  private def processBindings = actionsAndAssertions map {
    case b @ Binding(data, concept, display) => Binding(concept.process(data), concept, display)
  }

  def display(context: DC) = <html><body>Monkeys</body></html>
  def displayExecuted(context: DC) = ???
}

object Test {
  // A binding needs no context to be executable - data + concept = executable
  case class Binding[T, DC<:DisplayContext](data: T, concept: Concept[T], display: Display[T, DC])
}