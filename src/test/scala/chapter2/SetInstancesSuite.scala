package com.melalex.cats
package chapter2

import org.scalacheck.Gen
import org.scalactic.anyvals.PosZInt
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class SetInstancesSuite extends AnyFlatSpec with ScalaCheckPropertyChecks with MonoidLaws with Matchers {

  implicit override val generatorDrivenConfig: PropertyCheckConfiguration =
    PropertyCheckConfiguration(minSize = 5, sizeRange = PosZInt(20))

  private val setGen = Gen.containerOf[Set, Int](Gen.choose(1, 100))

  private val conformsAssociativeLaw = Table(
    "test_instance",
    SetInstances.setUnionMonoid[Int](),
    SetInstances.setIntersectionSemigroup[Int](),
    SetInstances.setDifferenceMonoid[Int]()
  )

  private val conformsIdentityLaw = Table(
    "test_instance",
    SetInstances.setUnionMonoid[Int](),
    SetInstances.setDifferenceMonoid[Int]()
  )

  private val associativeLawArgGen = for {
    x <- setGen
    y <- setGen
    z <- setGen
  } yield (x, y, z)

  "SetInstances" should "conform associative law" in {
    forAll(conformsAssociativeLaw) { implicit testInstance =>
      forAll(associativeLawArgGen) { case (x, y, z) => associativeLaw(x, y, z) shouldEqual true }
    }
  }

  it should "conform identity law" in {
    forAll(conformsIdentityLaw)(implicit testInstance => forAll(setGen)(x => identityLaw(x) shouldEqual true))
  }
}
