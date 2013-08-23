package doc.jockey

import example.project.dj.SupportedProductTypes
import example.project.main.{LchScm, LchFcm}
import org.scalatest.WordSpec

class DocJockeyBootstrapSpec extends WordSpec with DocJockeySpec {

  // A renderer, to show this as HTML
  // A GUI to edit this
  // A GUI to generate new ones

  // GENERATED CODE - DO NOT EDIT (unless you REALLY know what you are doing)
  "This DocJockey test" in {
    import example.project.dj.TradeWorkflow._

    // |no limits|auto accept|
    command(noLimits(), autoAccept())

    // |Supported Product Types|
    // |Description|Clearing House|Vanilla|FRA  |VNS  |
    // |desc       |LCH-FCM       |tick   |cross|tick |
    // |desc       |LCH-FCM       |tick   |tick |cross|
    table(supportedProductTypes(settings = None, rows =
      SupportedProductTypes.row("desc", LchFcm, true, false, true),
      SupportedProductTypes.row("desc", LchScm, true, true, false)
    ))

    // |pass|fail|exception|
    // |  22|   0|        0|
    summary
  }
}