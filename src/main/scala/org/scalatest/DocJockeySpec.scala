package org.scalatest

import doc.jockey.model.JustACommand
import doc.jockey.runners._

trait DocJockeySpec extends Suite with Assertions with BeforeAndAfterAll { thisSuite =>
  private[scalatest] val writer = new OutputWriter(this.getClass)
  private[scalatest] val engine: EngineDriver = new EngineDriver {}

  def specify(specTitle: String)(commands: JustACommand*) = {
    val testFun = () => {
      val runner = DocJockeyRunner(specTitle, commands.toList)
      writer.write(runner.output)
      assert(runner.summary.isAPass, "DocJockey test failed: " + specTitle + "\n" + runner.summary)
    }

    if (ignore) engine.registerIgnoredTest(specTitle, testFun)
    else engine.registerTest(specTitle, testFun)
  }

  private def ignore: Boolean = this.getClass.getPackage.getName contains "sandbox"

  override protected def afterAll(): Unit = writer.close()
  override def testNames = engine.testNames
  override def tags = Map.empty // We don't do any test method annotation lookups; there isn't a test method.

  // Instead of invoking a test method, we wrap the TestLeaf in an anonymous test method
  protected override def runTest(testName: String, reporter: Reporter, stopper: Stopper, configMap: Map[String, Any], tracker: Tracker) {

    def invokeWithFixture(theTest: engine.TestLeaf) {
      val theConfigMap = configMap
      withFixture(
        new NoArgTest {
          def name = testName
          def apply() { theTest.testFun() }
          def configMap = theConfigMap
        }
      )
    }

    engine.runTestImpl(thisSuite, testName, reporter, stopper, configMap, tracker, invokeWithFixture)
  }
}

trait EngineDriver {
  private[scalatest] val engine = new Engine("DjSpecMod", "DjSpec")
  type TestLeaf = engine.TestLeaf

  def registerTest(specTitle: String, testFun: () => Unit) = engine.registerTest(specTitle, testFun, "noResourceNameRequired", "DocJockeySpec.scala", "noMethodNameRequired", 1, None, None)
  def registerIgnoredTest(specTitle: String, testFun: () => Unit) = engine.registerIgnoredTest(specTitle, testFun, "noResourceNameRequired", "DocJockeySpec.scala", "noMethodNameRequired", 1)
  def testNames = engine.atomic.get.testNamesList.toSet
  def runTestImpl(thisSuite: Suite, testName: String, reporter: Reporter, stopper: Stopper, configMap: Map[String, Any], tracker: Tracker, invokeWithFixture: TestLeaf => Unit)
    = engine.runTestImpl(thisSuite, testName, reporter, stopper, configMap, tracker, includeIcon = true, invokeWithFixture)
}