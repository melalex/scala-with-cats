package com.melalex.cats
package chapter1.printable

trait PrintableInstances {

  implicit val printableInt: Printable[Int]       = _.toString
  implicit val printableString: Printable[String] = identity

  implicit def boxPrintable[A](implicit printable: Printable[A]): Printable[Box[A]] = printable.contramap(_.value)
}

object PrintableInstances extends PrintableInstances
