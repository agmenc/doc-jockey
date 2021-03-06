package doc.jockey.rendering

import doc.jockey.model.After
import doc.jockey.model.Before
import doc.jockey.model.Fail
import example.project.fixture.SupportedProductTypes.Expected
import example.project.fixture._
import example.project.main._
import org.scalatest.WordSpec

class TestNodeRenderingSpec extends WordSpec with HtmlAssertions {

  "Single-row Befores render themselves" in {
    assertEqual(Before(ComputerIs(true)).renderTable, <table class="table table-condensed table-bordered"><tr><th>Computer is</th><td class="success">on</td></tr></table>)
  }

  "Single-row Afters render themselves" in {
    assertEqual(After(ComputerIs(true), List(Fail("off", "on")), Nil).renderTable, <table class="table table-condensed table-bordered"><tr><th>Computer is</th><td class="danger"><span class="expectedText">off</span><span class="actualText">on</span></td></tr></table>)
  }

  val multiRowCommand =
    SupportedProductTypes(
      List(Vanilla, Fra, Vns),
      List(
        Expected("some desc", LchFcm, true, false, true),
        Expected("some other desc", LchScm, true, true, true)
      )
    )

  "Multi-row Befores render themselves" in {
    val expected =
      <table class="table table-condensed table-bordered">
        <thead>
          <tr><th>Product types supported</th></tr>
          <tr><td>Description</td><td>Clearing house</td><td>Vanilla</td><td>FRA</td><td>VNS</td></tr>
        </thead>
        <tbody>
          <tr><td>some desc</td><td>LCH-FCM</td><td class="success">✓</td><td class="success">-</td><td class="success">✓</td></tr>
          <tr><td>some other desc</td><td>LCH-SCM</td><td class="success">✓</td><td class="success">✓</td><td class="success">✓</td></tr>
        </tbody>
      </table>
    
    assertEqual(Before(multiRowCommand).renderTable, expected)
  }

  "Multi-row Afters render themselves" in {
    val expected =
      <table class="table table-condensed table-bordered">
        <thead>
          <tr><th>Product types supported</th></tr>
          <tr><td>Description</td><td>Clearing house</td><td>Vanilla</td><td>FRA</td><td>VNS</td></tr>
        </thead>
        <tbody>
          <tr><td>some desc</td><td>LCH-FCM</td><td class="success">✓</td><td class="success">-</td><td class="success">✓</td></tr>
          <tr><td>some other desc</td><td>LCH-SCM</td><td class="success">✓</td><td class="success">✓</td><td class="danger"><span class="expectedText">✓</span><span class="actualText">-</span></td></tr>
        </tbody>
      </table>

    val after = Before(multiRowCommand).execute
    assertEqual(after.renderTable, expected, "Multi 2")
  }
}