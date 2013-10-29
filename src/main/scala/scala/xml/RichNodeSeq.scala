package scala.xml

object RichNodeSeq {
  implicit class RichNodeSeq(ns: NodeSeq) {
    // Gather the other ns pimps and place them here
    // Spin off an improved XML lib
  }

  // TODO - CAS - 29/10/2013 - This is just a monoid. Do this generically, to provide a sum() method.
  implicit class RichSeqNode(sn: Iterable[NodeSeq]) {
    def toNodeSeq: NodeSeq = sn.foldLeft(NodeSeq.Empty)((ns, n) => ns ++ n)
  }
}