import Dependencies._
import Util._

ThisBuild / organization := "dev.insideyou"
ThisBuild / scalaVersion := "2.13.8"

lazy val scala3 =
  project
    .in(file("."))
    .settings(commonSettings)
    .aggregate(core, delivery, google, main)

lazy val core =
  project
    .in(file("01-core"))
    .settings(dependencies)
    .settings(commonSettings)

lazy val delivery =
  project
    .in(file("02-delivery"))
    .dependsOn(core % Cctt)
    .settings(commonSettings)

lazy val google =
  project
    .in(file("02-google"))
    .dependsOn(core % Cctt)
    .settings(commonSettings)

lazy val main =
  project
    .in(file("03-main"))
    .dependsOn(delivery % Cctt)
    .dependsOn(google % Cctt)
    .settings(commonSettings)

lazy val commonSettings =
  compilerPlugins ++ commonScalacOptions ++ Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

lazy val compilerPlugins = Seq(
  addCompilerPlugin(com.olegpy.`better-monadic-for`),
  addCompilerPlugin(org.augustjune.`context-applied`),
  addCompilerPlugin(org.typelevel.`kind-projector`),
)

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions := {
    (Compile / console / scalacOptions)
      .value
      .filterNot(_.contains("wartremover"))
      .filterNot(Scalac.Lint.toSet)
      .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
  },
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio" % "2.0.0-RC6"
  ),
  libraryDependencies ++= Seq(
    com.github.alexarchambault.`scalacheck-shapeless_1.15`,
    org.scalacheck.scalacheck,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-16`,
    org.typelevel.`discipline-scalatest`,
  ).map(_ % Test),
)
