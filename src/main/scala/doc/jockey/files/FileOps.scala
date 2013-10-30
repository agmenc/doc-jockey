package doc.jockey.files

import doc.jockey.Config.provider
import doc.jockey.files.FileOps._
import scala.Pimps._
import scala.reflect.io._

object FileOps {
  implicit class ClassToFilePimps(callingSpec: Class[_]) {
    def packageDir = callingSpec.getPackage.getName replaceAll ("\\.", File.separator)
    def fileName = callingSpec.getSimpleName + ".html"
  }
}

abstract class FileOps(caller: Class[_], baseDir: Path = provider.baseDir) {
  def file: File = tee((dir / caller.fileName).toFile)(_.createFile())
  def dir: Directory = tee((baseDir / caller.packageDir).toDirectory)(_.createDirectory())
}