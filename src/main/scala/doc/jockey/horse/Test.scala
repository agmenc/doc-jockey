package doc.jockey.horse

// Tests are also data
// Aggregates disparate data sources, finds the appropriate Concepts and sends the results to a display
case class Test(title: String, display: TestDisplay, actionsAndAssertions: (Data, Concept, ConceptDisplay)*) {
   type Binding = (Data, Concept, ConceptDisplay)
   def execute: (Binding, Binding) = ???
 }