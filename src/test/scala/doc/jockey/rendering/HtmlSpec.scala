package doc.jockey.rendering

import org.scalatest.WordSpec

class HtmlSpec extends WordSpec {

}

object HtmlSpec {
  def headerFor(caller: Class[_]) = Html(caller).header
}