package doc.jockey.model

trait JustACommand {
  def execute: List[Result]
  def title: String
  def expecteds: List[String]
  def inCode: String = this.toString
}