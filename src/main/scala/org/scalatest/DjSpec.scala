package org.scalatest

trait DjSpec extends Suite { thisSuite =>

  private final val engine = new Engine("concurrentWordSpecMod", "WordSpec")
  import engine._
  
  def specify(specTitle: String)(testFun: => Unit) = registerDjSpec(specTitle, testFun _)

  private def registerDjSpec(specTitle: String, testFun: () => Unit) =
    registerTest(specTitle, testFun, "noResourceNameRequired", "DjSpec.scala", "noMethodNameRequired", 1, None, None)

  override def testNames = Set("Support for product types by clearing house")

  override def tags = Map.empty

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