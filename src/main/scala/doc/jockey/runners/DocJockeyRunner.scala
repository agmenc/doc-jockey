package doc.jockey.runners

import doc.jockey.model._
import scala.xml.NodeSeq

case class DocJockeyRunner(commands: List[JustACommand]) {
  private lazy val befores: List[Before] = commands.map(Before)
  private lazy val afters: List[After] = befores.map(_.execute)

  def summary = afters.map(_.summary).foldLeft(Summary.empty)(_ + _)
  def output = afters.foldLeft(NodeSeq.Empty)((ns, r) => ns ++ r.render)
}