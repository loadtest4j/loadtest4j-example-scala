package org.loadtest4j.examples.scala

import org.scalatest.{FlatSpec, Matchers}

class ExampleIntegrationSpec extends FlatSpec with Matchers {
  it should "integration test the truth" in {
    true shouldBe true
  }
}
