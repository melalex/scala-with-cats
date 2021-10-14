package com.melalex.cats
package validation

import cats.data.{Kleisli, NonEmptyList}
import cats.implicits._

class TestApp extends App {

  case class User(name: String, age: Int)

  type Errors      = NonEmptyList[String]
  type Result[A]   = Either[Errors, A]
  type Check[A, B] = Kleisli[Result, A, B]

  def error(s: String): NonEmptyList[String] = NonEmptyList(s, Nil)

  def longerThan(n: Int): Predicate[Errors, String] =
    Predicate.lift(error(s"Must be longer than $n characters"), str => str.length > n)

  val alphanumeric: Predicate[Errors, String] =
    Predicate.lift(error(s"Must be all alphanumeric characters"), str => str.forall(_.isLetterOrDigit))

  def contains(char: Char): Predicate[Errors, String] =
    Predicate.lift(error(s"Must contain the character $char"), str => str.contains(char))

  def containsOnce(char: Char): Predicate[Errors, String] =
    Predicate.lift(error(s"Must contain the character $char only once"), str => str.count(c => c == char) == 1)

  val checkUsername: Check[String, String] = Kleisli((longerThan(3) and alphanumeric).run)

  def checkUser(user: User) = (
    checkUsername(user.name),
    user.age.valid
  ).mapN(User.apply _)
}
