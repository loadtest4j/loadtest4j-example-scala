package org.loadtest4j.examples.scala

import java.time.Duration

import org.loadtest4j.Request
import org.scalatest.Matchers

class ExampleLoadSpec extends LoadSpec with Matchers {

  behavior of "GET /"

  private val requests = Seq(Request.get("/"))

  private lazy val result = LoadTester.run(requests)

  it should "meet the Percent OK threshold" in {
    result.getPercentOk should be >= 99.99
  }

  it should "meet the p90 Response Time threshold" in {
    result.getResponseTime.getPercentile(90) should be <= Duration.ofMillis(500)
  }
}