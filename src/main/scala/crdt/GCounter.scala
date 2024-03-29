package com.melalex.cats
package crdt

import crdt.KeyValueStore.KvsOps

import cats.implicits._
import cats.kernel.CommutativeMonoid

trait GCounter[F[_, _], K, V] {

  def increment(f: F[K, V])(k: K, v: V)(implicit m: CommutativeMonoid[V]): F[K, V]

  def merge(f1: F[K, V], f2: F[K, V])(implicit b: BoundedSemiLattice[V]): F[K, V]

  def total(f: F[K, V])(implicit m: CommutativeMonoid[V]): V
}

object GCounter {

  implicit def gCounterInstance[F[_, _], K, V](implicit kvs: KeyValueStore[F], km: CommutativeMonoid[F[K, V]]): GCounter[F, K, V] =
    new GCounter[F, K, V] {

      def increment(f: F[K, V])(key: K, value: V)(implicit m: CommutativeMonoid[V]): F[K, V] = {
        val total = f.getOrElse(key, m.empty) |+| value
        f.put(key, total)
      }

      def merge(f1: F[K, V], f2: F[K, V])(implicit b: BoundedSemiLattice[V]): F[K, V] = f1 |+| f2

      def total(f: F[K, V])(implicit m: CommutativeMonoid[V]): V = f.values.combineAll
    }

  def apply[F[_, _], K, V](implicit counter: GCounter[F, K, V]): GCounter[F, K, V] = counter
}
