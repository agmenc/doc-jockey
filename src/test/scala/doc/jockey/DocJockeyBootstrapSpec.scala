package doc.jockey

import example.project.fixture._
import example.project.fixture.SupportedTradeTypes.Expected
import example.project.main._
import org.scalatest.DocJockeySpec

class DocJockeyBootstrapSpec extends DocJockeySpec {

  // |Computer is|on|

  // |Supported workflows|LCH-FCM|Manual, Netting|

  // |Product types supported|
  // |Description|Clearing House|Vanilla| FRA | VNS |
  // |desc       |LCH-FCM       |   ✓   |  -  |  ✓  |
  // |desc       |LCH-SCM       |   ✓   |  ✓  |  -  |

  // |pass|fail|exception|
  // |  22|   0|        0|

  // GENERATED CODE - DO NOT EDIT (unless you REALLY know what you are doing)
  specify("Trade clearing engine supports correct workflows and product types")(
    ComputerIs(true),
    SupportedWorkflows(LchFcm, List(Manual, Netting)),
    SupportedTradeTypes(
      List(Vanilla, Fra, Vns),
      List(
        Expected("some desc", LchFcm, true, false, true),
        Expected("some other desc", LchScm, true, true, false)
      )))
}