package doc.jockey.runners

import doc.jockey.model._

case class DocJockeyRunner(commands: List[JustACommand]) {
  private lazy val befores: List[Before] = commands.map(Before)
  private lazy val afters: List[After] = befores.map(_.execute)

  def summary = afters.flatMap(after => after.summary :: after.children.map(_.summary)).foldLeft(Summary.empty)(_ + _)
}