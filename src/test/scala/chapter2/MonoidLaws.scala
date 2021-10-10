package com.melalex.cats
package chapter2

trait MonoidLaws {

  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Semigroup[A]): Boolean = {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }
}

object MonoidLaws extends MonoidLaws
