package doc.jockey.horse

import fi.iki.elonen._
import fi.iki.elonen.core._
import NanoHTTPD._
import java.io.File
import doc.jockey.horse.pages._

/*
Adapter to hide away whichever simple HTTP server implementation I settle on
 */
class NanoRunner(host: String, port: Int, root: File) extends SimpleWebServer("0.0.0.0", port, root, false) {
  override def serve(session: IHTTPSession): Response = {
    def serveFile(any: String): Response = NanoRunner.super.serve(session)

    val uri: String = session.getUri
    println(s"uri: ${uri}")
    RequestDispatcher.respond.applyOrElse(uri, serveFile)
  }
}

case class HtmlResponse(responder: Page) extends Response(responder.render.toString())

object NanoRunner extends App {
  new NanoRunner("0.0.0.0", 8080, new File("./src/main/resources")).start()
  System.in.read
}