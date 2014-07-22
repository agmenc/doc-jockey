package doc.jockey.horse

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

// From the data, instantiate new Test classes at runtime (in the web app, and in the main codebase)
// Can we generate new instances of ScalaTest WordSpec at runtime, so that we get nice test logging?
class NewIdeaSpec extends WordSpec with MustMatchers {
  "We can execute an empty test" in {
    val emptyTest = Test("Bootstrap")
    assert(emptyTest.execute === TestResult("Bootstrap", Nil, Nil))
  }

  "We can execute a populated test" in {
    val concept = new MultiplicationEngine
    val linearTable = new LinearTable

    val input = Binding(Seq(4, 5, 6, 42), concept, linearTable)
    val output = Binding(Seq(4, 5, 6, 120), concept, linearTable)

    assert(Test("Bootstrap", input).execute === TestResult("Bootstrap", Seq(input), Seq(output)))
  }

  "We can display a test before it is executed" in {
    val concept = new MultiplicationEngine
    val linearTable = new LinearTable

    val expected = Binding(Seq(4, 5, 6, 42), concept, linearTable)

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


  Principle     A design schematic should have a spatial link to the system under design
  Principle     Specs should help a user visualise how a system works, so they can reason about it
  Insight
  Yes/No test

   */

  // In the web app, the Universe is constructed every time the app starts up
  // We might need some macro goodness to detect all the available subtypes of Concept and Shape
  object Universe {
    // sigh
    val me = new MultiplicationEngine
    val lt = new LinearTable

    // What about constructor parameters? They must be data, as well.
    val concepts = Map(
      classOf[MultiplicationEngine].getName -> (() => me)
    )
    val shapes = Map(
      classOf[LinearTable].getName -> (() => lt)
    )
  }

  "We can construct an executable test from data" in {
    // Read this in from somewhere - just serialise to/from disk for the moment?
    val title = "Monkeys"
    val preBindings = Seq(
      (Static(Seq(2, 4, -42)), "doc.jockey.horse.MultiplicationEngine", "doc.jockey.horse.LinearTable")
    )

    val expected = preBindings.map {
      case (datasource, conceptName, shapeName) => Binding(datasource.read, Universe.concepts(conceptName)(), Universe.shapes(shapeName)())
    }

    val actual = Seq(Binding(Seq(2, 4, 8), Universe.me, Universe.lt))

    assert(Test("monkeys", expected: _*).execute === TestResult("monkeys", expected, actual))
  }


  // Now go and write a little app so we can start at the beginning of the workflow


  // Test: we can build many test instances from source data
}

class MultiplicationEngine extends Concept[Seq[Int]] {
  override def process(data: Seq[Int]) = {
    val inputs = data.take(data.size - 1).toList
    val output = inputs.foldLeft(1)((acc, v) => acc * v)
    (output :: (inputs reverse)).reverse
  }
}

class LinearTable extends Shape