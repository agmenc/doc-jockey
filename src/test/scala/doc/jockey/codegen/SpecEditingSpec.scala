package doc.jockey.codegen

import doc.jockey.rendering.HtmlAssertions
import example.project.fixture.ComputerIs
import org.scalatest.WordSpec
import example.project.specs.ExampleSpec

class SpecEditingSpec extends WordSpec with HtmlAssertions {
  "We can edit, save, recompile and reload an existing spec" in {
    // Load data structure from spec
    val spec = new ExampleSpec

    // Add a simple command to the list of commands

    // Find the source on the file system

    // Re-write the source code to the file system
  }
  
  "We can find all specs" in {
    fail()
  }

  // Add spec: TestNodeSelfWritingSpec or something. They should be able to write themselves.
}