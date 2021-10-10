package com.melalex.cats
package chapter4

import cats.data.{OptionT, Reader}
import cats.implicits.catsSyntaxEq

class LoginSystem {

  type DbReader[A] = Reader[Db, A]

  case class Db(
      usernames: Map[Int, String],
      passwords: Map[String, String]
  )

  def findUsername(userId: Int): DbReader[Option[String]] = Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username) === Some(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] = OptionT(findUsername(userId))
    .semiflatMap(username => checkPassword(username, password))
    .getOrElse(false)
}
