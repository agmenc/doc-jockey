package doc.jockey

import example.project.fixture.ComputerIs
import example.project.fixture.SupportedWorkflows
import example.project.main._
import org.scalatest.DocJockeySpec

class DocJockeyBootstrapSpec extends DocJockeySpec {

  // |Computer is|on|

  // |Supported workflows|LCH-FCM|Manual, Netting|

  // |Supported product types|
  // |Description|Clearing House|Vanilla| FRA | VNS |
  // |desc       |LCH-FCM       |   ✓   |  -  |  ✓  |
  // |desc       |LCH-SCM       |   ✓   |  ✓  |  -  |

  // |pass|fail|exception|
  // |  22|   0|        0|

  // GENERATED CODE - DO NOT EDIT (unless you REALLY know what you are doing)
  specify("Trade clearing engine supports correct workflows and product types") (
    ComputerIs(true),
    SupportedWorkflows(LchFcm, List(Manual, Netting))
    //    ,
    //    SupportedProductTypes(
    //      List("Description", "Clearing House", "Vanilla", "FRA", "VNS"),
    //      List("desc", "LCH-FCM", "Yes", "-", "Yes"),
    //      List("desc", "LCH-SCM", "-", "Yes", "Nope"))
    //  )
  )
}