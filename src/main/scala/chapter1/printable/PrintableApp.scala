package com.melalex.cats
package chapter1.printable

import chapter1.Cat

object PrintableApp extends App with PrintableSyntax {

  implicit val printableCat: Printable[Cat] = cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."

  Cat.Mark.print
}
