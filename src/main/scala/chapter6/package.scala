package com.melalex.cats

import cats.Monad
import cats.implicits._

package object chapter6 {

  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] = for {
    a <- x
    b <- y
  } yield (a, b)
}
