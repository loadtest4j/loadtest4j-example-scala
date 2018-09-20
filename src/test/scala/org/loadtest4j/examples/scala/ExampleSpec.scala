package org.loadtest4j.examples.scala

import org.scalatest.{FlatSpec, Matchers}

class ExampleSpec extends FlatSpec with Matchers {
  it should "unit test the truth" in {
    true shouldBe true
  }
}
