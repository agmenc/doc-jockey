package doc.jockey.horse

import fi.iki.elonen._
import fi.iki.elonen.NanoHTTPD._
import java.io.File

class NanoRunner(host: String, port: Int, root: File) extends SimpleWebServer("0.0.0.0", port, root, false) {
  override def serve(session: IHTTPSession): Response = {
    def serveFile(any: String): Response = NanoRunner.super.serve(session)

    respond.applyOrElse(session.getUri, serveFile)
  }

  def respond: PartialFunction[String, Response] = {
    case "/monkeys" => new Response("You have found monkeys")
  }
}

object NanoRunner extends App {
  new NanoRunner("0.0.0.0", 8080, new File("src/main/webapp")).start()
  System.in.read
}