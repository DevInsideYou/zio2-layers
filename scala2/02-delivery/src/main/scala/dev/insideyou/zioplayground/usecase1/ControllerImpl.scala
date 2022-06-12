package dev.insideyou
package zioplayground
package usecase1

import zio._

object ControllerImpl {
  lazy val live =
    ZLayer.fromFunction(make _)

  def make(boundary: Boundary, console: Console): Controller =
    new Controller {
      override lazy val run =
        for {
          _ <- console.printLine("─" * 100)

          cats <- boundary.doesGoogleHaveEvenAmountOfPicturesOf("cats")
          _ <- console.printLine(cats)

          dogs <- boundary.doesGoogleHaveEvenAmountOfPicturesOf("dogs")
          _ <- console.printLine(dogs)

          _ <- console.printLine("─" * 100)
        } yield ()
    }
}
