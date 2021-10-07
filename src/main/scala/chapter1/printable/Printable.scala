package com.melalex.cats
package chapter1.printable

trait Printable[-A] {

  def format(a: A): String
}

object Printable {

  def format[A](a: A)(implicit printable: Printable[A]): String = printable.format(a)

  def print[A](a: A)(implicit printable: Printable[A]): Unit = println(printable.format(a))
}
