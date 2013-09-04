package doc.jockey

import example.project.main.{TradeClearingEngine, LchScm, LchFcm}
import org.scalatest.{DocJockeySpec, WordSpec}
import example.project.dj.clearing._

class DocJockeyBootstrapSpec extends DocJockeySpec {

  // GENERATED CODE - DO NOT EDIT (unless you REALLY know what you are doing)
  specify("DocJockey works") {
//    subSystem(TradeClearingEngine)

    // |Product Types Supported|LCH-SCM|Vanilla, VNS|
    command(SupportedWorkflows("LCH-FCM", List("Manual", "Netting")))

    // |Supported Product Types|
    // |Description|Clearing House|Vanilla| FRA | VNS |
    // |desc       |LCH-FCM       |   ✓   |  -  |  ✓  |
    // |desc       |LCH-SCM       |   ✓   |  ✓  |  -  |
    table(SupportedProductTypes,
      header("Description", "Clearing House", "Vanilla", "FRA", "VNS"),
      row("desc", "LCH-FCM", "Yes", "-", "Yes"),
      row("desc", "LCH-SCM", "Yes", "Yes", "-")
    )

    // |pass|fail|exception|
    // |  22|   0|        0|
    summary
  }
}