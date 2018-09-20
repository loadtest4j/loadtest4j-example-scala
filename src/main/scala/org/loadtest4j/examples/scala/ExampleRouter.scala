package org.loadtest4j.examples.scala

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.{Directives, Route}

object ExampleRouter extends Directives {
  val route: Route = pathSingleSlash {
      get {
        complete(OK, "Hello world")
      }
    }

}
