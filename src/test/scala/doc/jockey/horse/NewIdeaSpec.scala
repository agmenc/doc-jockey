package doc.jockey.horse

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import Test._

// From the data, instantiate new Test classes at runtime (in the web app, and in the main codebase)
// Can we generate new instances of ScalaTest WordSpec at runtime, so that we get nice test logging?
class NewIdeaSpec extends WordSpec with MustMatchers {
  "We can execute an empty test" in {
    val emptyTest = Test("Bootstrap")
    assert(emptyTest.execute === emptyTest)
  }

  class MultiplicationEngine extends Concept[Seq[Int]] {
    override def process(data: Seq[Int]) = {
      val inputs = data.take(data.size - 1).toList
      val output = inputs.foldLeft(1)((acc, v) => acc * v)
      (output :: (inputs reverse)).reverse
    }
  }

  // TODO - CAS - 03/07/2014 - This should be able to display Seq[Any], instead of being forced to be Seq[Int]
  class LinearHtmlDisplay extends Display[Seq[Int], Html.type]

  "We can execute a populated test" in {
    val concept = new MultiplicationEngine
    val display = new LinearHtmlDisplay

    val input = Binding(Seq(4, 5, 6, 42), concept, display)
    val output = Binding(Seq(4, 5, 6, 120), concept, display)

    assert(Test("Bootstrap", input).execute === Test("Bootstrap", output))
  }

  "We can display a test before it is executed" in {
    val concept = new MultiplicationEngine
    val display = new LinearHtmlDisplay

    val expected = Binding(Seq(4, 5, 6, 42), concept, display)

    assert(Test("Bootstrap", expected).display(Html) === <html><body>Monkeys</body></html>)
  }

  // We can build many test instances from source data
}
