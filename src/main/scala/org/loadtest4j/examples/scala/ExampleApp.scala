package org.loadtest4j.examples.scala

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object ExampleApp extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val routerWithLogging = DebuggingDirectives.logRequestResult("router with logging", Logging.InfoLevel)(ExampleRouter.route)

  Http().bindAndHandleAsync(Route.asyncHandler(routerWithLogging), "localhost", 3000)
}