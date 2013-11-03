package doc.jockey

import scala.reflect.io._

trait Config {
  def targetDir: Path
  def classpathResourcesDir: String
  def testResourcesDir: String

  Config.provider = this
}

object Config {
  var provider: Config = new DefaultConfig
}

class DefaultConfig extends Config {
  override val targetDir = Path(".") / "target" / "doc-jockey"
  override val classpathResourcesDir = "doc-jockey"
  override val testResourcesDir = "doc-jockey"
}