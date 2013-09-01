package doc.jockey.model

trait Command {
  def execute: Summary
}