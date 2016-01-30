name := "play-slick-compiletime-di-sample"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.restfb" % "restfb" % "1.14.0",
  "com.typesafe.play" %% "play-slick" % "1.1.1", // Slick 3.1
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
