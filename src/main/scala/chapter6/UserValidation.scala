package com.melalex.cats
package chapter6

import cats.data.{NonEmptyList, Validated}
import cats.implicits._

object UserValidation {

  case class User(name: String, age: Int)

  def validate(user: User): Validated[NonEmptyList[String], User] = (
    validateName(user.name),
    validateAge(user.age)
  ).mapN(User.apply)

  private def validateName(name: String): Validated[NonEmptyList[String], String] =
    if (name == null || name.isEmpty) Validated.invalidNel("Name should not be empty")
    else Validated.validNel(name)

  private def validateAge(age: Int): Validated[NonEmptyList[String], Int] =
    if (age < 0) Validated.invalidNel("Age should be positive")
    else Validated.validNel(age)
}
