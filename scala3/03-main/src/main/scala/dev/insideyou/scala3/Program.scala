package dev.insideyou
package scala3

import zio.*

lazy val program =
  usecase1
    .make
    .flatMap(_.run)
    .provide(Console.live /*ZLayer.Debug.mermaid*/ )
