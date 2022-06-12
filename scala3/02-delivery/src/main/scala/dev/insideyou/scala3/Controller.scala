package dev.insideyou
package scala3

import zio.*
import java.io.IOException

trait Controller:
  def run: IO[IOException, Unit]
