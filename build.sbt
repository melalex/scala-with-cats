name             := "scala-with-cats"
version          := "0.1"
scalaVersion     := "2.13.6"
idePackagePrefix := Some("com.melalex.cats")

libraryDependencies ++= {
  val catsVersion          = "2.6.1"
  val scalaTestVersion     = "3.2.10"
  val scalaTestPlusVersion = "3.2.9.0"

  Seq(
    "org.typelevel" %% "cats-core" % catsVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "org.scalatestplus" %% "scalacheck-1-15" % scalaTestPlusVersion % Test
  )
}
