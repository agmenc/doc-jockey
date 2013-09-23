package org.scalatest

import doc.jockey.model.JustACommand
import doc.jockey.runners.DocJockeyRunner


trait DocJockeySpec extends Suite with Assertions { thisSuite =>

  private final val engine = new Engine("DjSpecMod", "DjSpec")
  import engine._
  
  def specify(specTitle: String)(commands: JustACommand*) = registerDjSpec(specTitle, () => {
    val runner = DocJockeyRunner(commands.toList)
    assert(runner.summary.isAPass, "DocJockey test failed: " + specTitle + "\n" + runner.summary)
  })

  private def registerDjSpec(specTitle: String, testFun: () => Unit) =
    registerTest(specTitle, testFun, "noResourceNameRequired", "DocJockeySpec.scala", "noMethodNameRequired", 1, None, None)

  override def testNames = atomic.get.testNamesList.toSet

  // Don't do any test method annotation lookups; there isn't a test method.
  override def tags = Map.empty

  // Instead of invoking a test method, wrap the TestLeaf in an anonymous test method
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