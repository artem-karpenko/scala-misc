import java.io.FileOutputStream
import java.text.SimpleDateFormat
import org.joda.time.Duration
import org.joda.time.format.{PeriodFormatterBuilder, PeriodFormatter}

/**
 * @author yozh
 */
object Main {
   val periodFormatter: PeriodFormatter = new PeriodFormatterBuilder()
         .appendHours().appendSuffix(" h").appendSeparator(" ").appendMinutes().appendSuffix(" m")
         .appendSeconds().appendSuffix(" s")
         .toFormatter;

   def main(args: Array[String]): Unit = {
      println("Enter name of task")
      val task = new Task(readLine())

      while (true) {
         println("Next command")
         val cmd = readLine()

         cmd.toLowerCase() match {
            case "start" => task startWork
            case "stop" => task stopWork
            case "exit" => exitWork(task); return
            case _ => println("Unknown command: " + cmd)
         }
      }
   }

   def exitWork(task: Task) = {
      if (task.workInProgress) {
         task.stopWork()
      }
      println("Exiting, total time worked on task " + task.name + ": " +
            periodFormatter.print(new Duration(task.totalTime).toPeriod()))
   }
}
