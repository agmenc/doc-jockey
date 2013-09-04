package doc.jockey.engine

import doc.jockey.model.{Command}
import doc.jockey.builder.Summary

class DocJockey(commands: Seq[Command]) {
  def execute: Summary = commands.map(_.execute).reduce(_ + _)
}