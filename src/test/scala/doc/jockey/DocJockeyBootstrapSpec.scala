package doc.jockey

import org.scalatest.DocJockeySpec
import example.project.dj.clearing._

class DocJockeyBootstrapSpec extends DocJockeySpec {

  // |Computer is|on|

  // |Supported workflows|LCH-SCM|Manual, Netting|

  // |Supported product types|
  // |Description|Clearing House|Vanilla| FRA | VNS |
  // |desc       |LCH-FCM       |   ✓   |  -  |  ✓  |
  // |desc       |LCH-SCM       |   ✓   |  ✓  |  -  |

  // |pass|fail|exception|
  // |  22|   0|        0|

  // GENERATED CODE - DO NOT EDIT (unless you REALLY know what you are doing)
  val testModel = List(
    ComputerIsNow(true),
    SupportedWorkflows("LCH-FCM", List("Manual", "Netting"))
//    ,
//    SupportedProductTypes(
//      List("Description", "Clearing House", "Vanilla", "FRA", "VNS"),
//      List("desc", "LCH-FCM", "Yes", "-", "Yes"),
//      List("desc", "LCH-SCM", "-", "Yes", "Nope"))
  )

  specify("Executing the list of commands results in a list of executed commands") {
    assert(false === true)
  }

  specify("Executed Commands can render themselves") {
    assert(false === true)
  }
}