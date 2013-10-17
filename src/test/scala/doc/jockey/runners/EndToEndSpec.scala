package doc.jockey.runners

import example.project.fixture.SupportedTradeTypes.Expected
import example.project.fixture._
import example.project.main._
import org.scalatest._
import org.scalatest.events.Event
import scala.reflect.io.File

class EndToEndSpec extends WordSpec {
  "We can run a spec in a different class, and check the output file" in {
    val target = new OutputWriter(classOf[SomeDocJockeySpec]).file
    target.deleteIfExists()
    assert(!target.exists)

    (new SomeDocJockeySpec).run(Some("Some test description"), new VeryQuietReporter(), new Stopper(){}, new Filter(None, Set.empty), Map(), None, new Tracker())

    // TODO - CAS - 17/10/2013 - Merge the Bootstrap spec in here, and check for the expected contents. We should
    //                           have one - and one only - place where we expect verbatim an entire chunk of HTML
    assert(target.exists)
  }

  class VeryQuietReporter extends Reporter {
    def apply(event: Event) = {} // swallow
  }

  class SomeDocJockeySpec extends DocJockeySpec {
    specify("Some test description")(
      ComputerIs(true),
      SupportedWorkflows(LchFcm, List(Manual, Netting)),
      SupportedTradeTypes(
        List(Vanilla, Fra, Vns),
        List(
          Expected("some desc", LchFcm, true, false, true),
          Expected("some other desc", LchScm, true, true, false)
        )
      )
    )
  }
}