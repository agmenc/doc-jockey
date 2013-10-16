package org.scalatest

import doc.jockey.model.JustACommand
import doc.jockey.runners._

trait DocJockeySpec extends Suite with Assertions with BeforeAndAfterAll { thisSuite =>
  private val writer = new OutputWriter(this.getClass)
  private val engine = new Engine("DjSpecMod", "DjSpec")

  import engine._

  def specify(specTitle: String)(commands: JustACommand*) = registerDjSpec(specTitle, () => {
    val runner = DocJockeyRunner(specTitle, commands.toList)
    assert(runner.summary.isAPass, "DocJockey test failed: " + specTitle + "\n" + runner.summary)
    writer.write(runner.output)
  })

  override protected def afterAll(): Unit = writer.close()

  private def registerDjSpec(specTitle: String, testFun: () => Unit) =
    registerTest(specTitle, testFun, "noResourceNameRequired", "DocJockeySpec.scala", "noMethodNameRequired", 1, None, None)

  override def testNames = atomic.get.testNamesList.toSet

  // We don't do any test method annotation lookups; there isn't a test method.
  override def tags = Map.empty

  // Instead of invoking a test method, we wrap the TestLeaf in an anonymous test method
  protected override def runTest(testName: String, reporter: Reporter, stopper: Stopper, configMap: Map[String, Any], tracker: Tracker) {

    def invokeWithFixture(theTest: TestLeaf) {
      val theConfigMap = configMap
      withFixture(
        new NoArgTest {
          def name = testName
          def apply() { theTest.testFun() }
          def configMap = theConfigMap
        }
      )
    }

    runTestImpl(thisSuite, testName, reporter, stopper, configMap, tracker, true, invokeWithFixture)
  }
}