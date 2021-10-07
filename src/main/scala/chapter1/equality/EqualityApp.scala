package com.melalex.cats
package chapter1.equality

import chapter1.Cat

import cats.Eq
import cats.implicits._

object EqualityApp extends App {

  implicit val catsEq: Eq[Cat] = (cat1, cat2) =>
    cat1.name === cat2.name &&
    cat1.age === cat2.age &&
    cat1.color === cat2.color

  println(s"${Cat.Heathcliff} === ${Cat.Garfield} --> ${Cat.Heathcliff === Cat.Garfield}")
  println(s"${Cat.Heathcliff} =!= ${Cat.Garfield} --> ${Cat.Heathcliff =!= Cat.Garfield}")
}
