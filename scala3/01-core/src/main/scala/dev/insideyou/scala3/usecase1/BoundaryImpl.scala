package dev.insideyou
package scala3
package usecase1

import zio.*

object BoundaryImpl:
  lazy val live =
    ZLayer.fromFunction(make)

  def make(google: Google): Boundary =
    new:
      override def doesGoogleHaveEvenAmountOfPicturesOf(topic: String) =
        google.countPicturesOf(topic).map(_ % 2 == 0)
