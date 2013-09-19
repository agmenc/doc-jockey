package doc.jockey.model

case class Summary(pass: Int, fail: Int) {
  def +(that: Summary) = Summary(pass + that.pass, fail + that.fail)
}

object Summary {
  def apply(results: Iterable[Result]): Summary = results.map(Summary(_)).foldLeft(empty)((sum, next) => sum + next)

  def apply(result: Result): Summary = result match {
    case p: Pass => Summary(1, 0)
    case f: Fail => Summary(0, 1)
    case s: Setup => Summary(0, 0)
  }

  def empty = Summary(0, 0)
}