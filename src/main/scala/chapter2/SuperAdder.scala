package com.melalex.cats
package chapter2

import cats.syntax.semigroup._
import cats.{Monoid => CMonoid}

object SuperAdder {

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid :CMonoid[Order] = new CMonoid[Order] {

    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }

  def add[A](items: List[A])(implicit monoid: CMonoid[A]): A = items.fold(monoid.empty)((a, b) => a |+| b)
}
