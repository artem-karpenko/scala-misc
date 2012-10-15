import java.text.SimpleDateFormat
import org.joda.time.Duration
import org.joda.time.format.{PeriodFormatterBuilder, PeriodFormatter}

/**
 * @author yozh
 */
object Main {
   var totalTime: Long = 0
   var workInProcess = false
   var startTime: Long = -1
   var stopTime: Long = -1

   val periodFormatter: PeriodFormatter = new PeriodFormatterBuilder()
         .appendHours().appendSuffix(" h").appendSeparator(" ").appendMinutes().appendSuffix(" m")
         .appendSeconds().appendSuffix(" s")
         .toFormatter;

   def main(args: Array[String]): Unit = {
      while (true) {
         val cmd = readLine()

         cmd.toLowerCase() match {
            case "start" => startWork()
            case "stop" => stopWork()
            case "exit" => exitWork(); return
            case _ => println("Unknown command: " + cmd)
         }
      }
   }

   def startWork() = {
      if (workInProcess) {
         println("Error: work already started")
      }
      else {
         startTime = System.currentTimeMillis()
         workInProcess = true
         println("Work started")
      }
   }

   def stopWork() = {
      if (!workInProcess) {
         println("Error: work not started")
      }
      else {
         stopTime = System.currentTimeMillis()
         workInProcess = false

         val timeWorked = stopTime - startTime
         totalTime += timeWorked
         println("Work stopped, time worked: " + periodFormatter.print(new Duration(timeWorked).toPeriod()))
      }
   }

   def exitWork() = {
      if (workInProcess) {
         stopWork()
      }
      println("Exiting, total time worked: " + periodFormatter.print(new Duration(totalTime).toPeriod()))
   }
}
