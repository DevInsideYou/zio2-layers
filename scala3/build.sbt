import Dependencies._
import Util._

ThisBuild / organization := "dev.insideyou"
ThisBuild / scalaVersion := "3.1.2"

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-explain",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Yexplicit-nulls", // experimental (I've seen it cause issues with circe)
    "-Ykind-projector",
    "-Ysafe-init", // experimental (I've seen it cause issues with circe)
  ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future-migration")

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

lazy val commonSettings = commonScalacOptions ++ Seq(
  update / evictionWarningOptions := EvictionWarningOptions.empty
)

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions --= Seq(
    "-Wunused:_",
    "-Xfatal-warnings",
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio" % "2.0.0-RC6"
  ),
  libraryDependencies ++= Seq(
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-16`,
  ).map(_ % Test),
)
