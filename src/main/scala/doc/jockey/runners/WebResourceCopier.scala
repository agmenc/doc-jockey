package doc.jockey.runners

import java.io._
import scala.Pimps._
import scala.reflect.io.Path

object WebResourceCopier {
  // TODO - CAS - 02/11/2013 - Do the copy once only
}

class WebResourceCopier(resourcesDir: String, targetDir: Path, sources: String*) {
  private def copy(source: String) = transferText(getClass.getClassLoader.getResourceAsStream(resourcesDir + File.separator + source), target(source).toFile.bufferedOutput())
  private def target(source: Path) = tee(targetDir / source)(_.parent.toDirectory.createDirectory())

  private def transferText(is: InputStream, os: OutputStream) = {
    val printer = new java.io.PrintStream(os)
    val reader = new BufferedReader(new InputStreamReader(is))
    Iterator.continually(reader.readLine()).takeWhile(_ != null).map(printer.print)
    printer.close()
  }

  sources.map(copy)
}