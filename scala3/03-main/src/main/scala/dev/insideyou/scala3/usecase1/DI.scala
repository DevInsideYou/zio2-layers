package dev.insideyou
package scala3
package usecase1

import zio.*

def makeWithConstructors(console: Console) =
  ZIO.succeed {
    ControllerImpl.make(
      BoundaryImpl.make(
        GoogleImpl.make
      ),
      console,
    )
  }

lazy val make =
  ZIO
    .service[Controller]
    .provideSome[Console](
      ControllerImpl.live,
      BoundaryImpl.live,
      GoogleImpl.live,
      // ZLayer.Debug.mermaid,
    )
