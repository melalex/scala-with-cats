package com.melalex.cats
package crdt

import cats.kernel.CommutativeMonoid

trait BoundedSemiLattice[A] extends CommutativeMonoid[A] {

  def combine(a1: A, a2: A): A

  def empty: A
}
