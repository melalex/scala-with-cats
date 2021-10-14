package com.melalex.cats
package validation

import cats.data.Validated
import cats.implicits._
import cats.kernel.Semigroup

sealed trait Predicate[E, A]

final case class Pure[E, A](func: A => Validated[E, A]) extends Predicate[E, A]

final case class And[E, A](left: Predicate[E, A], right: Predicate[E, A]) extends Predicate[E, A]

final case class Or[E, A](left: Predicate[E, A], right: Predicate[E, A]) extends Predicate[E, A]

object Predicate {

  def apply[E, A](f: A => Validated[E, A]): Predicate[E, A] = Pure(f)

  def lift[E, A](err: E, fn: A => Boolean): Predicate[E, A] = Pure(a => if (fn(a)) a.valid else err.invalid)

  def run[E, A](predicate: Predicate[E, A])(implicit s: Semigroup[E]): A => Either[E, A] = a => predicate(a).toEither

  def run[E, A](predicate: Predicate[E, A])(implicit s: Semigroup[E]): A => Either[E, A] = a => predicate(a).toEither

  def run[E, A](value: A, predicate: Predicate[E, A])(implicit semigroup: Semigroup[E]): Validated[E, A] =
    predicate match {
      case Pure(func)       => func(value)
      case And(left, right) => (run(value, left), run(value, right)).mapN((_, _) => value)
      case Or(left, right)  => left(value).recoverWith(le => right(value).leftMap(re => le |+| re))
    }

  implicit class PredicateOps[E, A](self: Predicate[E, A]) {

    def run(implicit s: Semigroup[E]): A => Either[E, A] = Predicate.run(self)

    def apply(value: A)(implicit semigroup: Semigroup[E]): Validated[E, A] = Predicate.run(value, self)

    def and(other: Predicate[E, A]): Predicate[E, A] = And(self, other)

    def or(other: Predicate[E, A]): Predicate[E, A] = Or(self, other)
  }
}
