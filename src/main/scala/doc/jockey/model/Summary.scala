package doc.jockey.model

case class Summary(pass: Int, fail: Int, exception: Int) {
  def +(that: Summary) = Summary(pass + that.pass, fail + that.fail, exception + that.exception)
  def status = (fail + exception, pass) match {
    case (0, 0) => Empty
    case (0, p) if p > 0 => Pass
    case _ => Fail
  }
}

object Summary {
  lazy val empty = Summary(0, 0, 0)
}

trait SummaryStatus
case object Pass extends SummaryStatus
case object Fail extends SummaryStatus
case object Empty extends SummaryStatus