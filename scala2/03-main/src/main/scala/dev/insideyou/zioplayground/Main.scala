package dev.insideyou
package zioplayground

import zio._

object Main extends ZIOAppDefault {
  override lazy val run =
    Program.make.exitCode
}
