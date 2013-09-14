package doc.jockey.model

import org.scalatest.WordSpec

class DataTreeSpec extends WordSpec {
  class DummyCommand {
    def execute = ???
  }

  "An executed command generates a summary" in {
    new DummyCommand().execute
  }

  // TODO - CAS - 04/09/2013 - Zippers, anyone?
  "We can map the data tree to an executed data tree" in {
    assert(true === false)
  }

  "We can map an executed data tree to HTML" in {
    assert(true === false)
  }

  "We can map any data tree to HTML" in {
    assert(true === false)
  }
}