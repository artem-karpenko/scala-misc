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

   /**
    * TODO: Refactor: make tasks independent of users, count time for tasks for each user separately
    */

   def main(args: Array[String]): Unit = {
      while (true) {
         println("Next command")
         val cmd = readLine()

         try {
            cmd.toLowerCase().trim() match {
               case "login" => println("Username and password"); Session.login(readLine(), readLine())
               case "logout" => Session.logout; println("You are logged out")

               case "add task" => println("Task name"); getTaskManager().addTask(readLine())
               case "start" => println("Task name"); getTaskManager().startTask(readLine())
               case "stop" => println("Task name"); getTaskManager().stopTask(readLine())
               case "current" => println("Current task: " + getTaskManager().currentTask.getOrElse(EmptyTask))
               case "exit" => exitWork(); return
               case _ => println("Unknown command: " + cmd)
            }
         }
         catch {
            case e: TaskException => println("Error: " + e.getMessage)
            case e: SecurityException => println("Error: " + e.getMessage)
         }
      }
   }

   def exitWork() = {
      if (Session.isLoggedIn()) {
         Session.logout()
      }

      println("Exiting, total time worked on all tasks: ")
      UserManager.existingUsers.foreach((u: User) => {
         println("User " + u.login + " worked:");
         u.taskManager.tasks.foreach((t: Task) => {
            println("Task: " + t.name + ", time spent: " +
                  periodFormatter.print(new Duration(t.totalTime).toPeriod()))
         })
         println("Total time: " + periodFormatter.print(new Duration(u.taskManager.countTotalTime()).toPeriod()))
      })

      println("Total time working on all tasks by all users: " +
            periodFormatter.print(new Duration(UserManager.countTotalTime()).toPeriod()))
   }

   def getTaskManager() = {
      if (!Session.currentUser.isDefined) {
         throw new NotLoggedInException
      }
      TaskManager
   }
}
