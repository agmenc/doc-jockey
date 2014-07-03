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

    class MultiplicationEngine extends Concept[Seq[Int]] {
      override def process(data: Seq[Int]) = {
        val inputs = data.take(data.size - 1).toList
        val output = inputs.foldLeft(1)((acc, v) => acc * v)
        (output :: (inputs reverse)).reverse
      }
    }

    // // TODO - CAS - 03/07/2014 - This should be able to display Seq[Any], instead of being forced to be Seq[Int]
    class LinearHtmlDisplay extends Display[Seq[Int], Html.type]

    val concept = new MultiplicationEngine
    val display = new LinearHtmlDisplay

    val expected = Binding(Seq(4, 5, 6, 42), concept, display)
    val actual = Binding(Seq(4, 5, 6, 120), concept, display)

    val test = Test("Bootstrap", Html, expected)

    assert(test.execute === Seq(expected -> actual))
  }

  // We can build many test instances from source data
}
