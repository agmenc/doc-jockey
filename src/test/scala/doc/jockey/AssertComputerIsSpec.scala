package doc.jockey

import org.scalatest.{Assertions, WordSpec}
import example.project.dj.clearing.AssertComputerIs
import doc.jockey.model.Summary

class AssertComputerIsSpec extends WordSpec {
  "We can render a thingy" in {
    val expected = <table><tr><td>AssertComputerIs</td><td>On</td></tr></table>

    assert(AssertComputerIs("On").render === expected)
  }

  "We can execute a thingy and get an Executed thingy with a summary" in {
    assert(AssertComputerIs("On").execute.summary === Summary(1, 0, 0))
  }

  "We can render an executed thingy" in {
    val expected = <table><tr><td>AssertComputerIs</td><td>On</td></tr></table>

    assert(AssertComputerIs("On").render === expected)
  }
}