package com.melalex.cats
package chapter4

import cats.data.Writer
import cats.implicits.{catsSyntaxApplicativeId, catsSyntaxWriterId}

object Factorial {

  type Logged[A] = Writer[Vector[String], A]

  def slowly[A](body: => A): A =
    try body
    finally Thread.sleep(100)

  def factorial(n: Int): Logged[Int] = for {
    ans <- if (n == 0) 1.pure[Logged] else slowly(factorial(n - 1).map(_ * n))
    _   <- Vector(s"fact $n $ans").tell
  } yield ans
}
