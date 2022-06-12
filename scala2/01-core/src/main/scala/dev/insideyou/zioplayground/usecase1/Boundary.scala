package dev.insideyou
package zioplayground
package usecase1

import zio._

trait Boundary {
  def doesGoogleHaveEvenAmountOfPicturesOf(topic: String): UIO[Boolean]
}
