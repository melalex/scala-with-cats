package com.melalex.cats
package crdt

trait BoundedSemiLatticeInstances {

  implicit val boundedSemiLatticeForInt: BoundedSemiLattice[Int] = new BoundedSemiLattice[Int] {

    override def combine(a1: Int, a2: Int): Int = a1 max a2

    override def empty: Int = 0
  }

  implicit def boundedSemiLatticeForSet[A]: BoundedSemiLattice[Set[A]] = new BoundedSemiLattice[Set[A]] {

    override def combine(a1: Set[A], a2: Set[A]): Set[A] = a1 union a2

    override def empty: Set[A] = Set.empty
  }
}

object BoundedSemiLatticeInstances extends BoundedSemiLatticeInstances
