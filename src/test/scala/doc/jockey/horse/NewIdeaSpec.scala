package doc.jockey.horse

import org.scalatest.WordSpec

/*
Test(title, testDisplay, datasource -> concept, datasource -> concept, *)

From the data, instantiate new Test classes at runtime (in the web app, and in the main codebase)
Can we generate new instances of ScalaTest WordSpec at runtime, so that we get nice test logging?
 */

class NewIdeaSpec extends WordSpec {
  "Yup" in {
    val htmlDisplay = new Html {}
    Test("Bootstrap", htmlDisplay).execute
  }
}
