package dev.insideyou
package scala3
package usecase1

import zio.*

trait Boundary:
  def doesGoogleHaveEvenAmountOfPicturesOf(topic: String): UIO[Boolean]
