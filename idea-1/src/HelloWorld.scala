import java.text.{DateFormat, SimpleDateFormat}
import java.util.{Locale, Date}

object HelloWorld {
   def main(args: Array[String]) {
      val now = new Date()
      val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.GERMANY)
      println(df format now)
      //
      println(1.0+2)

      val input = Console.readLine()
      println(input)
   }
}