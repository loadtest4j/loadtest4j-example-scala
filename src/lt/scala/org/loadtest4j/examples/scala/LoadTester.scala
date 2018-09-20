package org.loadtest4j.examples.scala

import org.loadtest4j.factory.LoadTesterFactory
import org.loadtest4j.{Request, Result}

import scala.collection.JavaConverters._

object LoadTester {
  private lazy val loadTester = LoadTesterFactory.getLoadTester

  def run(requests: Seq[Request]): Result = {
    loadTester.run(requests.asJava)
  }
}
