package com.melalex.cats
package chapter4

import cats.Eval

object Fold {

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] = as match {
    case head :: tail => Eval.defer(foldRight(tail, acc)(fn).map(fn(head, _)))
    case Nil          => Eval.now(acc)
  }
}
