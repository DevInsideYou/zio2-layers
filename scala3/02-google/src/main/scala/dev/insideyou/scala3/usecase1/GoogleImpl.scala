package dev.insideyou
package scala3
package usecase1

import zio.*

object GoogleImpl:
  lazy val live =
    ZLayer.succeed(make)

  lazy val make: Google =
    new:
      override def countPicturesOf(topic: String) =
        ZIO.succeed(if topic == "cats" then 1337 else 1338)
