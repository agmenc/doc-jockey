package doc.jockey.horse

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

// From the data, instantiate new Test classes at runtime (in the web app, and in the main codebase)
// Can we generate new instances of ScalaTest WordSpec at runtime, so that we get nice test logging?
class NewIdeaSpec extends WordSpec with MustMatchers {
  "We can execute an empty test" in {
    assert(Test("Bootstrap", Html).execute === Nil)
  }

  "We can execute a populated test" in {
    import Test._

    class MultiplicationEngine[T] extends Concept[T] {
      def process(data: T): T = ???
    }

    class LinearHtmlDisplay extends Display[Seq[_], Html.type]

    val expected = Binding(Seq(4, 5, 6, 120), new MultiplicationEngine[Seq[_]], new LinearHtmlDisplay)

    val test = Test("Bootstrap", Html, expected)

    assert(test.execute === Seq(expected -> expected))
  }

  // We can build many test instances from source data
}
