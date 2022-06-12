package dev.insideyou
package scala3
package usecase1

import zio.*

trait Google:
  def countPicturesOf(topic: String): UIO[Int]
