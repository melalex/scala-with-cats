package com.melalex.cats
package chapter3

trait Codec[A] { self =>

  def encode(value: A): String

  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {

    override def encode(value: B): String = self.encode(enc(value))

    override def decode(value: String): B = dec(self.decode(value))
  }
}

object Codec {

  implicit val stringCodec: Codec[String] = new Codec[String] {
    def encode(value: String): String = value
    def decode(value: String): String = value
  }

  implicit val intCodec: Codec[Int] = stringCodec.imap(_.toInt, _.toString)

  implicit val doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)

  implicit val booleanCodec: Codec[Boolean] = stringCodec.imap(_.toBoolean, _.toString)

  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] = c.imap(Box(_), _.value)

  def encode[A](value: A)(implicit c: Codec[A]): String = c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A = c.decode(value)
}
