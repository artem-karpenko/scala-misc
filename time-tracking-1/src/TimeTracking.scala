import java.io.FileOutputStream
import java.text.SimpleDateFormat
import org.joda.time.Duration
import org.joda.time.format.{PeriodFormatterBuilder, PeriodFormatter}

/**
 * @author Artem
 */
object TimeTracking {
   val periodFormatter: PeriodFormatter = new PeriodFormatterBuilder()
         .appendHours().appendSuffix("h ").appendMinutes().appendSuffix("m ")
         .appendSeconds().appendSuffix("s")
         .toFormatter;

   val taskManager = new TaskManager()

   def main(args: Array[String]): Unit = {
      while (true) {
         println("Next command")
         val cmd = readLine()

         try {
            cmd.toLowerCase().trim() match {
               case "add task" => println("Task name"); taskManager.addTask(readLine())
               case "start" => println("Task name"); taskManager.startTask(readLine())
               case "stop" => println("Task name"); taskManager.stopTask(readLine())
               case "current" => println("Current task: " + taskManager.currentTask.getOrElse(EmptyTask))
               case "exit" => exitWork(); return
               case _ => println("Unknown command: " + cmd)
            }
         }
         catch {
            case e: TaskException => println("Error: " + e.getMessage)
         }
      }
   }

   def exitWork() = {
      taskManager.stopAllTasksIfAny()

      println("Exiting, total time worked on all tasks: " +
            periodFormatter.print(new Duration(taskManager.countTotalTime()).toPeriod()))
   }
}
