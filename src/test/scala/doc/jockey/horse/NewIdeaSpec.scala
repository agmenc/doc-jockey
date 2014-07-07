package doc.jockey.horse

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import Test._

// From the data, instantiate new Test classes at runtime (in the web app, and in the main codebase)
// Can we generate new instances of ScalaTest WordSpec at runtime, so that we get nice test logging?
class NewIdeaSpec extends WordSpec with MustMatchers {
  "We can execute an empty test" in {
    val emptyTest = Test("Bootstrap")
    assert(emptyTest.execute === TestResult("Bootstrap")())
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

    assert(Test("Bootstrap", input).execute === TestResult("Bootstrap", input)(output))
  }

  "We can display a test before it is executed" in {
    val concept = new MultiplicationEngine
    val display = new LinearHtmlDisplay

    val expected = Binding(Seq(4, 5, 6, 42), concept, display)

    assert(Test("Bootstrap", expected).display(Html) === <html><body>Monkeys</body></html>)
  }

  /*

  Inspired by Brett Victor - http://worrydream.com/

  Principle     Bureaucracy makes people unhappy and business unprofitable
  Insight       There must be a feedback loop in an organisation to remove bureaucracy, or else it will grow uncontrolled
  Yes/No test   Does the current activity result in higher profits?


  Principle
  Insight
  Yes/No test


  Principle     A design schematic should have a spatial link to the systm under design
  Principle     Users should have a language for specifying a system that shows them how it will work
  Insight
  Yes/No test

   */

  // Construct with code gen:
  object Universe {

    val poo: String = classOf[MultiplicationEngine].getSimpleName
    val displays = Map(
      // What about constructor parameters?
      classOf[MultiplicationEngine].getSimpleName -> (() => new MultiplicationEngine)
    )
  }

  "We can construct a test from data" in {
    val data =
      """
        |monkeys
        |2, 4, MultiplicationEngine, LinearHtmlDisplay
      """.stripMargin

//    Universe.concepts("MultiplicationEngine")
    Universe.displays("MultiplicationEngine")
  }

  // We can build many test instances from source data
}
