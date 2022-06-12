package dev.insideyou
package zioplayground
package usecase1

import zio._

trait Google {
  def countPicturesOf(topic: String): UIO[Int]
}
