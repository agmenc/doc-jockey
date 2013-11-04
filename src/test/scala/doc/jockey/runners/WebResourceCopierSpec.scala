package doc.jockey.runners

import doc.jockey.Config.provider
import org.scalatest.WordSpec

class WebResourceCopierSpec extends WordSpec {
  "Copies named resources to the target directory" in {
    val someResource = (provider.targetDir / "css" / "some.css").toFile
    someResource.deleteIfExists()
    assert(!someResource.exists, s"$someResource was not deleted")

    new WebResourceCopier(provider.classpathResourcesDir, provider.targetDir, "css/some.css")

    assert(someResource.exists, s"$someResource not found")
    assert(someResource.slurp().contains("\n/*Hello*/\n\n/*Goodbye*/\n\n"), s"$someResource did not contain expected text. Contents: [${someResource.slurp()}]")
  }
}