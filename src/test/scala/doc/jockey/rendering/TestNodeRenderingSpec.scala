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
    assertEqual(Before(ComputerIs(true)).renderTable, <table><tr><td>Computer is</td><td>on</td></tr></table>)
  }

  "Single-row Afters render themselves" in {
    assertEqual(After(ComputerIs(true), List(Fail("off", "on")), Nil).renderTable, <table><tr><td>Computer is</td><td><span class="failText">off</span>on</td></tr></table>)
  }

  val multiRowCommand =
    SupportedProductTypes(
      List(Vanilla, Fra, Vns),
      List(
        Expected("some desc", LchFcm, true, false, true),
        Expected("some other desc", LchScm, true, true, false)
      )
    )

  "Multi-row Befores render themselves" in {
    val expected =
      <table>
        <tr><td>Product types supported</td></tr>
        <tr><td>Description</td><td>Clearing house</td><td>Vanilla</td><td>FRA</td><td>VNS</td></tr>
        <tr><td>some desc</td><td>LCH-FCM</td><td>✓</td><td>-</td><td>✓</td></tr>
        <tr><td>some other desc</td><td>LCH-SCM</td><td>✓</td><td>✓</td><td>-</td></tr>
      </table>
    
    assertEqual(Before(multiRowCommand).renderTable, expected)
  }

  "Multi-row Afters render themselves" in {
    val expected =
      <table>
        <tr><td>Product types supported</td></tr>
        <tr><td>Description</td><td>Clearing house</td><td>Vanilla</td><td>FRA</td><td>VNS</td></tr>
        <tr><td>some desc</td><td>LCH-FCM</td><td>✓</td><td>-</td><td>✓</td></tr>
        <tr><td>some other desc</td><td>LCH-SCM</td><td>Do the faily thing ✓</td><td>✓</td><td>-</td></tr>
      </table>

    val after = Before(multiRowCommand).execute
    assertEqual(after.renderTable, expected, "Multi 2")
    fail()
  }
}