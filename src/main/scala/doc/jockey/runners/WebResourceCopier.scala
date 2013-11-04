package doc.jockey.runners

import doc.jockey.Config._
import java.io._
import java.net.URL
import scala.Pimps._
import scala.reflect.io.Path

object WebResourceCopier {
  def copy(sources: String*) = new WebResourceCopier(provider.classpathResourcesDir, provider.targetDir, sources:_*)

  copy("css/doc-jockey.css")
}

class WebResourceCopier(resourcesDir: String, targetDir: Path, sources: String*) {
  private def url(source: String): Option[URL] = Option(getClass.getClassLoader.getResource(resourcesDir + File.separator + source))
  private def target(source: String) = tee(targetDir / source)(_.parent.toDirectory.createDirectory())

  // TODO - CAS - 04/11/2013 - Switch to using byte buffers. This will only work for text files, and Stream.continually() will blow the stack for a large file
  private def copyText(is: InputStream, os: OutputStream) = {
    val printer = new java.io.PrintStream(os)
    val reader = new BufferedReader(new InputStreamReader(is))
    Stream.continually(reader.readLine()).takeWhile(_ != null).foreach(printer.println)
    printer.close()
  }

  for {
    source <- sources
    sourceUrl <- url(source)
  } copyText(sourceUrl.openStream(), target(source).toFile.bufferedOutput())
}