package doc.jockey.runners

import org.scalatest.WordSpec

class NewIdeaSpec extends WordSpec {

}

// Trees, tables, lists
// Data has shapes, Concepts bind to shapes
// Data must have a pre-executed and a post-executed format. Post-executed might be a transformation of the original
// Data may come from different data sources, e.g. CSV, XML, HTML, case classes, etc
trait Data {
  def map(tx: Elem => Elem)
}

trait Elem

// Map data in a certain shape to calls against the codebase
// Acts as a transform on the pre-executed data to the post-executed data
trait Concept[Shape]

// Renders the test result (pre- vs Option(post-)) in some display format
trait TestDisplay
trait Html extends TestDisplay
trait Text extends TestDisplay

// Displays the result of individual Concepts
trait ConceptDisplay[Shape, TestDisplayType]

// Tests are also data
// Aggregates disparate data sources, finds the appropriate Concepts and sends the results to a display
trait Test

//
trait Runner
trait BuildRunner extends Runner
trait WebAppRunner extends Runner

/*
Test(title, testDisplay, datasource -> concept, datasource -> concept, *)

From the data, instantiate new Test classes at runtime (in the web app, and in the main codebase)
Can we generate new instances of ScalaTest WordSpec at runtime, so that we get nice test logging?
 */