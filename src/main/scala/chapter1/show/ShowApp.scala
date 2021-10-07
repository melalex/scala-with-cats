package com.melalex.cats
package chapter1.show

import chapter1.Cat

import cats.Show
import cats.implicits._

object ShowApp extends App {

  implicit val showCat: Show[Cat] = cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."

  println(Cat.Mark.show)
}
