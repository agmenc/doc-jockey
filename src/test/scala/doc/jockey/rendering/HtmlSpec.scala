package doc.jockey.rendering

import org.scalatest.WordSpec
import doc.jockey.rendering.deep.down.DummyDeepPackage

class HtmlSpec extends WordSpec with HtmlAssertions {
  "The path to a resouce is relative to the package of the calling class" in  {
    assertContains(Html(classOf[HtmlSpec]).header, "link", <link href='../../../css/doc-jockey.css' rel='stylesheet' type='text/css'/>)
    assertContains(Html(classOf[DummyDeepPackage]).header, "link", <link href='../../../../../css/doc-jockey.css' rel='stylesheet' type='text/css'/>)
  }
}

object HtmlSpec {
  def headerFor(caller: Class[_]) = Html(caller).header
}