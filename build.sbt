ThisBuild / tlBaseVersion := "0.1"

ThisBuild / organization := "ai.dragonfly"
ThisBuild / organizationName := "dragonfly.ai"
ThisBuild / startYear := Some(2023)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List( tlGitHubDev("dragonfly-ai", "dragonfly.ai") )

ThisBuild / scalaVersion := "3.2.1"
ThisBuild / tlSonatypeUseLegacyHost := false
ThisBuild / tlSitePublishBranch := Some("main")

lazy val democrossy = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .settings(
  name := "democrossy",
  description := "Re-rout console output to a selectable DOM element in ScalaJS, without impeding default behavior in Scala JVM and Scala Native!",
  version := "0.1"
).jvmSettings(
  libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0"
).jsSettings(
  libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.2.0"
)

lazy val demo = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .enablePlugins(NoPublishPlugin)
  .dependsOn(democrossy)
  .settings(
    name := "demo",
    Compile / mainClass := Some("Demo"),
  )
  .jsSettings(
    Compile / fastOptJS / artifactPath := file("./docs/js/main.js"),
    Compile / fullOptJS / artifactPath := file("./docs/js/main.js"),
    scalaJSUseMainModuleInitializer := true
  ).jvmSettings(
    exportJars := true
  )


lazy val docs = project.in(file("site")).enablePlugins(TypelevelSitePlugin)