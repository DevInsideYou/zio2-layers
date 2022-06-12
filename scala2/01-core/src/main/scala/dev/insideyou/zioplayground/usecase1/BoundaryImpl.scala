package dev.insideyou
package zioplayground
package usecase1

import zio._

object BoundaryImpl {
  lazy val live =
    ZLayer.fromFunction(make _)

  def make(google: Google): Boundary =
    new Boundary {
      override def doesGoogleHaveEvenAmountOfPicturesOf(topic: String) =
        google.countPicturesOf(topic).map(_ % 2 == 0)
    }
}
