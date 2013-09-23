package doc.jockey.runners

import doc.jockey.model._
import doc.jockey.model.Before
import doc.jockey.model.After

case class DocJockeyRunner(commands: List[JustACommand]) {
  private lazy val afters: List[After] = commands.map(Before(_).execute)

  def summary = afters.map(_.summary).foldLeft(Summary.empty)(_ + _)
}