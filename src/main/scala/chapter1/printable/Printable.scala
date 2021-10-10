package com.melalex.cats
package chapter1.printable

trait Printable[-A] {

  def format(a: A): String

  def contramap[B](func: B => A): Printable[B] = (value: B) => format(func(value))
}

object Printable {

  def format[A](a: A)(implicit printable: Printable[A]): String = printable.format(a)

  def print[A](a: A)(implicit printable: Printable[A]): Unit = println(printable.format(a))
}
