package dev.insideyou
package scala3

import zio.*

object Main extends ZIOAppDefault:
  override lazy val run =
    program.exitCode
