package doc.jockey.horse

// Tests are also data
// Aggregates disparate data sources, finds the appropriate Concepts and sends the results to a display
// We could diff pre- and post- Bindings, and only show the diffs .... allows us to group all failures in one short output
case class Test[DC<:DisplayContext](title: String, actionsAndAssertions: Binding[_, DC]*) {
  def execute = TestResult(title, actionsAndAssertions:_*)(processBindings:_*)

  private def processBindings = actionsAndAssertions map {
    case b @ Binding(data, concept, display) => Binding(concept.process(data), concept, display)
  }

  def display(context: DC) = <html><body>Monkeys</body></html>
  def displayExecuted(context: DC) = ???
}

// Extends Test??
case class TestResult[DC<:DisplayContext](title: String, actionsAndAssertions: Binding[_, DC]*)(results: Binding[_, DC]*)

case class Binding[T, DC<:DisplayContext](data: T, concept: Concept[T], display: Display[T, DC])


  // A binding needs no context to be executable - data + concept = executable



  // Consider: Display[T], without display context. Then an executed test can be rendered in several different ways

/*
Creating a test:
  In the appropriate display context
  Read the available datasources and concepts
  Add a binding
    Choose a datasource/sink, or create a placeholder
    Choose a concept, or create a placeholder
  Add more bindings ...
  Write to the data sink
  Write code to the test file (codegen -> treehugger)

Running a test:
  Bindings -> datasource, concept, display type
    load the data, apply the concept
    if needed, generate the output display

 */



