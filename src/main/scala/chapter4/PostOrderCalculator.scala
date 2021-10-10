package com.melalex.cats
package chapter4

import cats.data.State

object PostOrderCalculator {

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def operand(num: Int): CalcState[Int] = State[List[Int], Int](stack => (num :: stack, num))

  def operator(func: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int] {
    case b :: a :: tail =>
      val ans = func(a, b)
      (ans :: tail, ans)
    case _ => sys.error("Fail!")
  }
}
