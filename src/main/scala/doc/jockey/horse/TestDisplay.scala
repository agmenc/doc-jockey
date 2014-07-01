package doc.jockey.horse

import org.scalatest.WordSpec


// Renders the test result (pre- vs Option(post-)) in some display format
trait TestDisplay
trait Html extends TestDisplay
trait Text extends TestDisplay

// Displays the result of individual Concepts
trait ConceptDisplay[Shape, TestDisplayType]
