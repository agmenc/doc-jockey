package doc.jockey.model

trait Command {
  def execute: Executed
}

trait Executed { this: Command =>
  def result: Summary
}