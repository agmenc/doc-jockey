package doc.jockey.model


trait Command {
  def execute: Summary
}

trait Leaf extends Command

trait Branch extends Command {
  def children: List[Command]
}

trait Executed { this: Command =>
  def result: Summary
}

/**
 *
 * Three mappings:
 *  map(_.execute) ===> result.map(_.display) ===> HTML output
 *  map(_.code) ===> test spec
 *  map(_.display) ===> editing

  \
  |
  |- Cmd
  |
  |- Cmd
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