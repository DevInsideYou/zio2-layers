package dev.insideyou
package zioplayground

import zio._

object Program {
  lazy val make =
    usecase1
      .DI
      .make
      .flatMap(_.run)
      .provide(
        Console.live,
        ZLayer.Debug.mermaid,
      )
}
