package doc.jockey.runners

import doc.jockey.rendering.HtmlAssertions
import example.project.fixture.SupportedProductTypes.Expected
import example.project.fixture._
import example.project.main._
import org.scalatest._
import org.scalatest.events.Event

class NewEndToEndSpec extends WordSpec with HtmlAssertions {
  // Clone or check out a DJ-enabled codebase (normally a DEVELOPER task)
  // Start an instance of the WYSIWYG server, with some git repo configured, on some port (normally a DEVELOPER task)
  // Clone an existing spec to a sandbox location (USER task)
  // Edit it in WYSIWYG (USER task)
  // Compile the generated spec (USER task)
  // Run it to check it fails in a good way, or that there is fixture required (USER task)
  // Commit just that spec back to the repo (USER task)
  // (kill the instance of the WYSIWYG server) (normally a DEVELOPER task)

  "It does everything" in {
    // Hmmmm
  }
}