package doc.jockey

import example.project.main.{TradeClearingEngine, LchScm, LchFcm}
import org.scalatest.WordSpec
import example.project.dj.clearing.{AutoAccept, NoLimits, SupportedProductTypes}

class DocJockeyBootstrapSpec extends WordSpec with DocJockeySpec {

  // A renderer, to show this as HTML
  // A GUI to edit this
  // A GUI to generate new ones

  // GENERATED CODE - DO NOT EDIT (unless you REALLY know what you are doing)
  "This DocJockey test" in {
    system(new TradeClearingEngine())

    // |no limits|auto accept|
    commands(NoLimits(), AutoAccept())

    // |Supported Product Types|
    // |Description|Clearing House|Vanilla| FRA | VNS |
    // |desc       |LCH-FCM       |   ✓   |  -  |  ✓  |
    // |desc       |LCH-FCM       |   ✓   |  ✓  |  -  |
    table(SupportedProductTypes,
      row("desc", "LCH-FCM", "Yes", "-", "Yes"),
      row("desc", "LCH-SCM", "Yes", "Yes", "-")
    )

    // |pass|fail|exception|
    // |  22|   0|        0|
    summary
  }
}