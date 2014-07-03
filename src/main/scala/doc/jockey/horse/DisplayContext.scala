package doc.jockey.horse

// Renders the test result (pre- vs Option(post-)) in some display format
trait DisplayContext {
  // Output format?
}
case object Html extends DisplayContext
case object Text extends DisplayContext

// Displays the result of individual Concepts
trait Display[T, Context <: DisplayContext]
