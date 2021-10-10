package com.melalex.cats
package chapter2

trait SetInstances {

  implicit def setUnionMonoid[A](): Monoid[Set[A]] = new Monoid[Set[A]]{

    override def empty: Set[A] = Set.empty

    override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y

    override def toString: String = "SetUnionMonoid"
  }

  implicit def setIntersectionSemigroup[A](): Semigroup[Set[A]] = new Semigroup[Set[A]]{

    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y

    override def toString: String = "SetUnionSemigroup"
  }

  implicit def setDifferenceMonoid[A](): Monoid[Set[A]] = new Monoid[Set[A]] {

    override def empty: Set[A] = Set.empty

    override def combine(x: Set[A], y: Set[A]): Set[A] = (x ++ y) -- (x intersect y)
  }
}

object SetInstances extends SetInstances
