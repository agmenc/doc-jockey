package doc.jockey.model

trait Table extends Command {
  def rows: Seq[List[String]]
}