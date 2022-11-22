ThisBuild / scalaVersion := "3.2.1"
ThisBuild / publishTo := Some( Resolver.file( "file",  new File("/var/www/maven" ) ) )
ThisBuild / resolvers += "ai.dragonfly.code" at "https://code.dragonfly.ai/"
ThisBuild / organization := "ai.dragonfly.code"
ThisBuild / scalacOptions ++= Seq("-feature", "-deprecation")

lazy val democrossy = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .settings(
  name := "democrossy",
  version := "0.02"
).jvmSettings(
  libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0"
).jsSettings(
  libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.2.0"
)

lazy val demo = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .dependsOn(democrossy)
  .settings(
    name := "demo",
    Compile / mainClass := Some("Demo"),
  )
  .jsSettings(
    Compile / fastOptJS / artifactPath := file("./demo/public_html/js/main.js"),
    Compile / fullOptJS / artifactPath := file("./demo/public_html/js/main.js"),
    scalaJSUseMainModuleInitializer := true
  ).jvmSettings(
    exportJars := true
  )
