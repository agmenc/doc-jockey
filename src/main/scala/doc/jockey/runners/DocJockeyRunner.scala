package doc.jockey.runners

import doc.jockey.model._
import scala.xml.NodeSeq
import scala.xml.RichNodeSeq._

case class DocJockeyRunner(specTitle: String, commands: Iterable[JustACommand]) {
  private lazy val afters = commands.map(Before(_).execute)

  def summary: Summary = afters.foldLeft(Summary.empty)(_ + _.summary)
  def output: NodeSeq = <h2>{specTitle}</h2> ++ afters.map(_.renderTable).toNodeSeq
}