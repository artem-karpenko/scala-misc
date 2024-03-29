/**
 * @author yozh
 */
object TimerAnonymous {
   def oncePerSecond(callback: () => Unit) {
      while (true) {
         callback()
         Thread.sleep(1000)
      }
   }

   def main(args: Array[String]) {
      oncePerSecond(() =>
         println("Time flies like an arrow"))
   }
}
