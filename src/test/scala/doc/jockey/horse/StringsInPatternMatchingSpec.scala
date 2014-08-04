package doc.jockey.horse

import org.scalatest.WordSpec

class StringsInPatternMatchingSpec extends WordSpec {

  implicit class PatternMatchableUrlAdapter(val sc: StringContext) {
    val url = sc.parts.mkString("(.+)").r
  }

  "We can pattern match in interpolated Strings" in {
    def matcher: PartialFunction[String, String] = {
      case url"this ${a} is a simple ${b}" => s"Matched: $a, $b"
      case url"/dj/$a/$b" => s"Matched: $a, $b"
      case x => "Did not match anything"
    }

    assert(matcher("this sentence is a simple string") === "Matched: sentence, string")
    assert(matcher("/dj/investment/StockAnalyser") === "Matched: investment, StockAnalyser")
  }

  "We can pattern match in interpolated Strings inside Tim's HTTP DSL" in {
    case class GET(x: String)

    def matcher: PartialFunction[GET, String] = {
      case GET(url"/trade/$tradeID/message/$messageID") => s"Matched: $tradeID, $messageID" //processTradeMEssage(tradeId, messageId)
      case x => "It fucked up"
    }

    assert(matcher(GET("/trade/123/message/456")) === "Matched: 123, 456")
  }
}