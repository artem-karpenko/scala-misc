/**
 * @author yozh
 */
object Timer {
   def oncePerSecond(callback: () => Unit) {
      while (true) {
         callback()
         Thread.sleep(1000)
      }
   }

   def timeFlies() {
      println("Time flies like an arrow")
   }

   def main(String: Array[String]) {
      oncePerSecond(timeFlies)
   }
}
