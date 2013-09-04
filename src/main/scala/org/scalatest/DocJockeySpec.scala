package org.scalatest

import doc.jockey.builder.DocJockeyBuilder

trait DocJockeySpec extends Suite with DocJockeyBuilder { thisSuite =>

  private final val engine = new Engine("DjSpecMod", "DjSpec")
  import engine._
  
  def specify(specTitle: String)(testFun: => Unit) = registerDjSpec(specTitle, testFun _)

  private def registerDjSpec(specTitle: String, testFun: () => Unit) =
    registerTest(specTitle, testFun, "noResourceNameRequired", "DjSpec.scala", "noMethodNameRequired", 1, None, None)

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