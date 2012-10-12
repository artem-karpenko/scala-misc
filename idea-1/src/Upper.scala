/**
 * @author yozh
 */
class Upper {
   def upper(strings: String*): Seq[String] = {
      strings.map(_.toUpperCase)
   }
}

object Main {
   def main(args: Array[String]) {
      println(new Upper().upper("A", "First"))
   }
}
