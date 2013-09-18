package doc.jockey

import doc.jockey.model._
import example.project.fixture.ComputerIs
import example.project.fixture.SupportedWorkflows
import example.project.main._
import org.scalatest.DocJockeySpec

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
    ComputerIs(false),
    SupportedWorkflows(LchFcm, List(Manual, Netting))
//    ,
//    SupportedProductTypes(
//      List("Description", "Clearing House", "Vanilla", "FRA", "VNS"),
//      List("desc", "LCH-FCM", "Yes", "-", "Yes"),
//      List("desc", "LCH-SCM", "-", "Yes", "Nope"))
  )

  specify("We can get an overall Summary of the test run") {
    assert(testModel.map(Before(_).execute).map(_.summary).foldLeft(Summary.empty)(_ + _) === Summary(4, 2))
  }

  specify("Executed Commands can render themselves") {
    assert(false === true)
  }
}