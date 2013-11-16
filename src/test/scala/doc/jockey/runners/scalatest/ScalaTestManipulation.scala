package doc.jockey.runners.scalatest

import org.scalatest._
import org.scalatest.events.Event

trait ScalaTestManipulation {
  def runSpec(spec: DocJockeySpec) {
    spec.run(None, new VeryQuietReporter(), new Stopper() {}, new Filter(None, Set.empty), Map(), None, new Tracker())
  }

  class VeryQuietReporter extends Reporter {
    def apply(event: Event) = {} // swallow
  }
}