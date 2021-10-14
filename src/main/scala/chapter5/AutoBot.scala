package com.melalex.cats
package chapter5

import cats.data.EitherT
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

object AutoBot {

  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] =
    EitherT.fromEither[Future](powerLevels.get(autobot).toRight(s"$autobot is unreachable"))

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = for {
    power1 <- getPowerLevel(ally1)
    power2 <- getPowerLevel(ally2)
  } yield power1 + power2 > 15

  def tacticalReport(ally1: String, ally2: String): String = {
    val brightFuture = canSpecialMove(ally1, ally2).value

    Await.result(brightFuture, 5 seconds) match {
      case Left(err)    => s"Comms error: $err"
      case Right(true)  => s"$ally1 and $ally2 are ready to roll out!"
      case Right(false) => s"$ally1 and $ally2 need a recharge."
    }
  }
}
