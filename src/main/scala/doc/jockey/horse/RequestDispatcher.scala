package doc.jockey.horse

import doc.jockey.horse.pages._
import fi.iki.elonen.core.Response

object RequestDispatcher {
  implicit class MySContext (val sc : StringContext) {
    object mys {
      def apply (args : Any*) : String = sc.s (args : _*)
      def unapplySeq (s : String) : Option[Seq[String]] = {
        val regexp = sc.parts.mkString ("(.+)").r
        regexp.unapplySeq (s)
      }
    }
  }

  def respond: PartialFunction[String, Response] = {
    case "" | "/" => HtmlResponse(Index)
    case mys"/test/${name}" => HtmlResponse(TestRep(name))
  }
}

case class TestRep(name: String) extends Page {
  override def render = <div>monkeys</div>
}