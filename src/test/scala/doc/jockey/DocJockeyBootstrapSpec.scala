package doc.jockey

import example.project.main.{TradeClearingEngine, LchScm, LchFcm}
import org.scalatest.{DocJockeySpec, WordSpec}
import example.project.dj.clearing._
import doc.jockey.model.{Command, Tree, Summary}

class DocJockeyBootstrapSpec extends DocJockeySpec {

  // GENERATED CODE - DO NOT EDIT (unless you REALLY know what you are doing)

  // |AssertComputerIs|On|

  // |SupportedWorkflows|LCH-SCM|Manual, Netting|

  // |Supported Product Types|
  // |Description|Clearing House|Vanilla| FRA | VNS |
  // |desc       |LCH-FCM       |   ✓   |  -  |  ✓  |
  // |desc       |LCH-SCM       |   ✓   |  ✓  |  -  |

  // |pass|fail|exception|
  // |  22|   0|        0|

  type Data = Either[String, List[Data]]
  type Element = Either[Command, Data]

  trait Cmd
  trait Extr
  case class SCmd(executor: Extr, data: Data*)

  val testModel = List(
    AssertComputerIs("On"),
    SupportedWorkflows("LCH-FCM", List("Manual", "Netting")),
    SupportedProductTypes(
      List("Description", "Clearing House", "Vanilla", "FRA", "VNS"),
      List("desc", "LCH-FCM", "Yes", "-", "Yes"),
      List("desc", "LCH-SCM", "-", "Yes", "Nope"))
  )

  case class Thing()
  val executed = List(
    Thing(SupportedWorkflows, "LCH-FCM", Diff(List("Manual", "Netting"), List("Manual"))),
    Thing(SupportedProductTypes,
      Types(List("Description", "Clearing House", "Vanilla", "FRA", "VNS")),
      Expect(List("desc", "LCH-FCM", "Yes", "-", "Yes")),
      Diff(List("desc", "LCH-SCM", "-", "Yes", "Nope"))
    )
  )

  specify("Executing the list of commands results in a list of executed commands") {
    assert(false === true)
  }

  specify("Executed Commands can render themselves") {
    assert(false === true)
  }
}