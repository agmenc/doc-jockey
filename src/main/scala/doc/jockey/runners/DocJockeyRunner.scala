package doc.jockey.runners

import doc.jockey.model._
import scala.xml.NodeSeq

case class DocJockeyRunner(specTitle: String, commands: Iterable[JustACommand]) {
  WebResourceCopier
  private lazy val afters = commands.map(Before(_).execute)

  def summary: Summary = afters.foldLeft(Summary.empty)(_ + _.summary)
  def output: NodeSeq = <div class="well well-lg"><h3>{specTitle}</h3>{afters.map(_.renderTable).flatten}</div>
}