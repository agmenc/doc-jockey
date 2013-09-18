package doc.jockey.model

import org.scalatest.WordSpec

class SummarySpec extends WordSpec {
  "We can build a Summary from results" in {
    assert(Summary(List(Pass("a"), Fail("a", "b"), Pass("c"))) === Summary(2, 1))
  }

  "We can sum Summaries" in {
    assert(Summary(2, 5) + Summary(9, 0) === Summary(11, 5))
  }
}