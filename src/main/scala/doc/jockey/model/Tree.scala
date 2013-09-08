package doc.jockey.model

trait Tree[T] extends Traversable[T] {
  def element: Option[T]
  def children: Option[Tree[T]]

  def foreach[U](f: (T) => U) {
    element.map(f)
    children.map(_.foreach(f))
  }
}

object Tree {
  def apply[T](kids: T): Tree[T] = ???
}

//case class Leaf[T](element: T) extends Tree[T] {
//  def children = None
//  def append(sibling: Tree[T]) = ???
//  def append(children: Iterable[Tree[T]]) = ???
//}
//
//case class Branch[T](context: T) extends Tree[T] {
//  def element = Some(context)
//  def children = ???
//  def append(sibling: Tree[T]) = ???
//  def append(children: Iterable[Tree[T]]) = ???
//}

/**
 * ListTree ===> TreeList?
 *  Each node knows the previous node
 *  Then it gets reversed
 *
 * Three mappings:
 *  map(_.execute) ===> result.map(_.display) ===> HTML output
 *  map(_.code) ===> test spec
 *  map(_.display) ===> editing

  \
  |
  |- (Cmd)
  |
  |- (Context/Cmd)
  |   |
  |   |- Cmd
  |   |
  |   |- Cmd
  |   |
  |   |- Cmd
  |
  |- Cmd
  |

 */