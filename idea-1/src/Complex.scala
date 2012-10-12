/**
 * @author yozh
 */
class Complex(real: Double, imaginary: Double) {
   def re = real
   def im = imaginary

   override def toString() =
      "" + re + (if (im > 0) "+" else "") + im
}

object Main {
   def main(args: Array[String]) {
      val c = new Complex(1.2, 2.5)
      println(c.re)
      println(c)
      val c2 = new Complex(4, -3)
      println(c2)
   }
}
