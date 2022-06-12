package dev.insideyou
package scala3
package usecase1

import zio.*

object ControllerImpl:
  lazy val live =
    ZLayer.fromFunction(make)

  def make(boundary: Boundary, console: Console): Controller =
    new:
      override lazy val run =
        for
          _ <- console.printLine("─" * 100)

          cats <- boundary.doesGoogleHaveEvenAmountOfPicturesOf("cats")
          _ <- console.printLine(cats)

          dogs <- boundary.doesGoogleHaveEvenAmountOfPicturesOf("dogs")
          _ <- console.printLine(dogs)

          _ <- console.printLine("─" * 100)
        yield ()
