package com.melalex.cats
package chapter2

trait BoolInstances {

  implicit val boolAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {

    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y

    override def toString: String = "BoolAndMonoid"
  }

  implicit val boolOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {

    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y

    override def toString: String = "BoolOrMonoid"
  }

  implicit val boolXorMonoid: Monoid[Boolean] = new Monoid[Boolean] {

    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x != y

    override def toString: String = "BoolXorMonoid"
  }

  implicit val boolXnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {

    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x == y

    override def toString: String = "BoolXnorMonoid"
  }
}

object BoolInstances extends BoolInstances
