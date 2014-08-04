package doc.jockey.horse

import io.shaka.http.ContentType._
import io.shaka.http.Status.NOT_FOUND
import io.shaka.http.HttpServer
import io.shaka.http.Response._
import io.shaka.http.Request._
import io.shaka.http.RequestMatching._

object AppRunner extends App {
  val httpServer = HttpServer(8080).handler{
    case GET(url"/tickets/$ticketId?messageContains=$messageContains") => respond(s"Ticket $ticketId, messageContains $messageContains").contentType(TEXT_PLAIN)
    case fail => respond(s"Page not found. Request was:\n$fail").status(NOT_FOUND)
  }.start()
}