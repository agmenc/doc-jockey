package doc.jockey.horse

import org.scalatest.WordSpec

class StringsInPatternMatchingSpec extends WordSpec {
  implicit class StringPatternMatcher(val sc : StringContext) {
    object s {
      def apply (args : Any*) : String = sc.s (args : _*)
      def unapplySeq (s: String*) : Option[Seq[String]] = {
        val regexp = sc.parts.mkString("(.+)").r
        regexp.unapplySeq (s)
      }
    }
  }

  implicit class MySContext (val sc : StringContext) {
    object mys {
      def apply (args : Any*) : String = sc.s (args : _*)
      def unapplySeq (s : String) : Option[Seq[String]] = {
        val regexp = sc.parts.mkString ("(.+)").r
        regexp.unapplySeq (s)
      }
    }
  }

  "We can pattern match in interpolated Strings" in {
    def matcher: PartialFunction[String, String] = {
      case mys"this ${a} is a simple ${b}" => s"Matched: $a, $b"
      case mys"/dj/$a/$b" => s"Matched: $a, $b"
      case x => "Did not match anything"
    }

    assert(matcher("this sentence is a simple string") === "Matched: sentence, string")
    assert(matcher("/dj/investment/StockAnalyser") === "Matched: investment, StockAnalyser")
  }
}