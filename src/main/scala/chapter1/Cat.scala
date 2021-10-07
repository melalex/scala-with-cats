package com.melalex.cats
package chapter1

final case class Cat(name: String, age: Int, color: String)

object Cat {

  val Mark: Cat       = Cat("Mark", 20, "orange")
  val Garfield: Cat   = Cat("Garfield", 38, "orange and black")
  val Heathcliff: Cat = Cat("Heathcliff", 33, "orange and black")
}
