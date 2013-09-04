package doc.jockey.builder

import doc.jockey.model.Command

trait Table extends Command {
  def rows: Seq[List[String]]
}