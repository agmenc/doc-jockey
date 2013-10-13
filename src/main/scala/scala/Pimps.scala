package scala

object Pimps {
  def tee[A](c: => A)(f: A => Unit): A = {
    val result: A = c
    f(result)
    result
  }
}