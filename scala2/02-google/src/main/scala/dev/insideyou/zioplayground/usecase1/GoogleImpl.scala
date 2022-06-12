package dev.insideyou
package zioplayground
package usecase1

import zio._

object GoogleImpl {
  lazy val live =
    ZLayer.succeed(make)

  lazy val make: Google =
    new Google {
      override def countPicturesOf(topic: String) =
        ZIO.succeed(if (topic == "cats") 1337 else 1338)
    }
}
