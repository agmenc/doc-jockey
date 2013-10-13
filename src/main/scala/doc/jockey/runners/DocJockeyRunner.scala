package doc.jockey.runners

import doc.jockey.model._
import scala.xml.NodeSeq

case class DocJockeyRunner(commands: Iterable[JustACommand]) {
  private lazy val afters = commands.map(Before(_).execute)

  def summary: Summary = afters.foldLeft(Summary.empty)(_ + _.summary)
  def output: NodeSeq = afters.foldLeft(NodeSeq.Empty)(_ ++ _.render)
}