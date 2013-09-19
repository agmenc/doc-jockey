package doc.jockey.model

trait JustACommand {
  def title: String
  def expecteds: List[Result]
  def actuals: List[String]

  def inCode: String = this.toString

  def execute: List[Result] = compare(expecteds zip actuals)

  private def compare(expectedsAndActuals: List[(Result, String)]) = expectedsAndActuals.map(_ match {
    case (exR, ac) if exR.expected == ac => exR
    case (exR, ac) => Fail(exR.expected, ac)
  })
}