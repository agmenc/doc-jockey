package doc.jockey.runners

import doc.jockey.Config._
import java.io._
import java.net.URL
import scala.Pimps._
import scala.reflect.io.Path

object WebResourceCopier {
  val copier = new WebResourceCopier(provider.classpathResourcesDir, provider.targetDir)
  copier.copyClasspathResources("css/doc-jockey.css", "css/bootstrap.min.css")
}

class WebResourceCopier(resourcesDir: String, targetDir: Path) {
  private def url(source: String): Option[URL] = Option(getClass.getClassLoader.getResource(resourcesDir + File.separator + source))
  private def target(source: String) = tee(targetDir / source)(_.parent.toDirectory.createDirectory())

  // TODO - CAS - 04/11/2013 - Switch to using byte buffers. This will only work for text files.
  private def copyText(is: InputStream, os: OutputStream) = {
    val printer = new java.io.PrintStream(os)
    val reader = new BufferedReader(new InputStreamReader(is))
    Iterator.continually(reader.readLine()).takeWhile(_ != null).foreach(printer.println)
    printer.close()
  }

  def copyClasspathResources(classpathResources: String*) =
    for {
      source <- classpathResources
      sourceUrl <- url(source)
    } copyText(sourceUrl.openStream(), target(source).toFile.bufferedOutput())
}