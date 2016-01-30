package test

import play.api.test.FakeRequest
import play.api.test.PlaySpecification
import test.TestEnvironment.WithApplicationComponents

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpecification {

  "Application" should {

    "send 404 on a bad request" in new WithApplicationComponents {
      val result = route(FakeRequest(GET, "/boum")).get
      status(result) mustEqual NOT_FOUND
    }

    "render the index page" in new WithApplicationComponents {
      val home = route(FakeRequest(GET, "/")).get

      status(home) mustEqual OK
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("kitty cat")
    }
  }
}
