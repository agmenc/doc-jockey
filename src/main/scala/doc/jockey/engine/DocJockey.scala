package doc.jockey.engine

import doc.jockey.model.{Command, Summary}

class DocJockey(commands: Seq[Command]) {
  def execute: Summary = new Summary
}