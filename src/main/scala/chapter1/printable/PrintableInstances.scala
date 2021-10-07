package com.melalex.cats
package chapter1.printable

trait PrintableInstances {

  implicit val printableInt: Printable[Int]       = _.toString
  implicit val printableString: Printable[String] = identity
}

object PrintableInstances extends PrintableInstances
