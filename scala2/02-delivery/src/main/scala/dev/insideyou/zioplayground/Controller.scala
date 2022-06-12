package dev.insideyou
package zioplayground

import zio._
import java.io.IOException

trait Controller {
  def run: IO[IOException, Unit]
}
