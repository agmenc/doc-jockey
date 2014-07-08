package doc.jockey.horse

// Tests are also data
// Aggregates disparate data sources, finds the appropriate Concepts and sends the results to a display
// We could diff pre- and post- Bindings, and only show the diffs .... allows us to group all failures in one short output
case class Test[S<:Shape](title: String, actionsAndAssertions: Binding[_, S]*) {
  def execute = TestResult(title, actionsAndAssertions:_*)(processBindings:_*)

  private def processBindings = actionsAndAssertions map {
    case b @ Binding(data, concept, display) => Binding(concept.process(data), concept, display)
  }

  def display(context: DisplayContext) = <html><body>Monkeys</body></html>
  def displayExecuted(context: S) = ???
}

// Extends Test??
case class TestResult[S<:Shape](title: String, actionsAndAssertions: Binding[_, S]*)(results: Binding[_, S]*)

case class Binding[T, S<:Shape](data: T, concept: Concept[T], shape: S)



  // ===> Shapes ===> A binding needs no context to be executable - data + concept = executable




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



