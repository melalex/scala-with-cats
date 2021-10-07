package com.melalex.cats
package chapter1.printable

trait PrintableSyntax {

  implicit class PrintableOps[A](a: A) {

    def format(implicit printable: Printable[A]): String = Printable.format(a)

    def print(implicit printable: Printable[A]): Unit = Printable.print(a)
  }
}

object PrintableSyntax extends PrintableSyntax
