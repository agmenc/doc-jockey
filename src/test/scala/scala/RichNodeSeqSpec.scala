package scala

import org.scalatest.WordSpec
import scala.xml.NodeSeq
import scala.xml.RichNodeSeq._

class RichNodeSeqSpec extends WordSpec {
  "We can concatenate a Seq[Node]" in {
    val iterable: Iterable[NodeSeq] = List[NodeSeq](<a></a>, <b></b>, <c></c>)

    assert(iterable.toNodeSeq === <a></a> ++ <b></b> ++ <c></c>)
  }

  "Concatenating an empty sequence gives an empty NodeSeq" in  {
    val iterable: Iterable[NodeSeq] = List[NodeSeq]()

    assert(iterable.toNodeSeq === NodeSeq.Empty)
  }
}