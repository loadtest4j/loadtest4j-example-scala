name := "loadtest4j-example-scala"

scalaVersion := "2.12.6"

val akkaHttpVersion = "10.1.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % "2.5.12",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "lt",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test,it,lt",

  //
  // loadtest4j dependency
  //

  "org.loadtest4j.drivers" % "loadtest4j-gatling" % "0.7.0" % "test,lt"
)

//
// load test group
//

val LoadTest = config("lt") extend Runtime
sourceDirectory in LoadTest := baseDirectory.value / "src/lt"

//
// default loadtest4j environment
//

systemPropertyVariables := Map(
  "loadtest4j.driver.duration" -> "5",
  "loadtest4j.driver.usersPerSecond" -> "1",
  "loadtest4j.driver.url" -> "http://localhost:3000"
)

//
// custom loadtest4j environments
//

commands += Command.single("profile") { (state: State, name: String) =>
  val profile = name match {
    case "staging" =>
      Map(
        "loadtest4j.driver.duration" -> "5",
        "loadtest4j.driver.usersPerSecond" -> "1",
        "loadtest4j.driver.url" -> "https://staging.example.com"
      )
    case "production" =>
      Map(
        "loadtest4j.driver.duration" -> "5",
        "loadtest4j.driver.usersPerSecond" -> "1",
        "loadtest4j.driver.url" -> "https://example.com"
      )
  }

  Project.extract(state).appendWithoutSession(Seq(systemPropertyVariables := profile), state)
}

//
// attach load test configuration
//

val systemPropertyVariables = settingKey[Map[String, String]]("test system property variables")
testOptions += Tests.Setup(() => sys.props ++= systemPropertyVariables.value)

val root = (project in file("."))
  .configs(IntegrationTest, LoadTest)
  .settings(Defaults.itSettings)
  .settings(inConfig(LoadTest)(Defaults.testSettings))
