package org.loadtest4j.examples.scala

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import org.scalatest.{BeforeAndAfterAll, FlatSpec}

import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.concurrent.duration._

trait LoadSpec extends FlatSpec with BeforeAndAfterAll {

  private implicit val system: ActorSystem = ActorSystem()
  private implicit val materializer: ActorMaterializer = ActorMaterializer()
  private implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  private var binding: Future[Http.ServerBinding] = _

  override def beforeAll(): Unit = {
    super.beforeAll()

    binding = Http().bindAndHandle(ExampleRouter.route, "localhost", 3000)
  }

  override def afterAll(): Unit = {
    Await.result(binding, 3.seconds)
      .terminate(hardDeadline = 3.seconds)
      .flatMap { _ =>
        system.terminate()
      }

    super.afterAll()
  }
}
