package com.melalex.cats
package chapter2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class BoolInstancesSuite extends AnyFlatSpec with TableDrivenPropertyChecks with MonoidLaws with Matchers {

  private val boolValues = Seq(true, false)

  private val associativeLawCombinations = for {
    x <- boolValues
    y <- boolValues
    z <- boolValues
  } yield (x, y, z)

  private val associativeLawArgTable = Table(("x", "y", "z")) ++ associativeLawCombinations
  private val identityLawArgTable    = Table("x", true, false)

  private val monoids = Table(
    "test_instance",
    BoolInstances.boolAndMonoid,
    BoolInstances.boolOrMonoid,
    BoolInstances.boolXorMonoid,
    BoolInstances.boolXnorMonoid
  )

  "BooleanInstances" should "conform associative law" in {
    forAll(monoids) { implicit monoid =>
      forAll(associativeLawArgTable) { case (x, y, z) => associativeLaw(x, y, z) shouldEqual true }
    }
  }

  it should "conform identity law" in {
    forAll(monoids)(implicit monoid => forAll(identityLawArgTable)(x => identityLaw(x) shouldEqual true))
  }
}
