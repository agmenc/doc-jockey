package doc.jockey

import scala.reflect.io._

trait Config {
  def baseDir: Path
  Config.provider = this
}

object Config {
  var provider: Config = new DefaultConfig
}

class DefaultConfig extends Config {
  override val baseDir = Path(".") / "target" / "doc-jockey"
}