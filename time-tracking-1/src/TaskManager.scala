import scala.collection.mutable
import scala.None

/**
 * Manager of tasks for user
 * @author Artem
 */
object TaskManager {
   val tasks: mutable.Set[Task] = new mutable.HashSet
   var currentTask: Option[Task] = Option(null)

   /**
    * Add task
    * @param name name of new task
    * @throws TaskAlreadyExistsException if task already exists
    */
   def addTask(name: String) = {
      if (taskExists(name)) {
         throw new TaskAlreadyExistsException(name)
      }

      val task: Task = new Task(name)
      tasks.add(task)

      task
   }

   def startTask(name: String) = {
      if (!taskExists(name)) {
         throw new TaskNotFoundException(name)
      }
      if (currentTask.isDefined) {
         throw new TaskAlreadyStartedException(currentTask.get.name)
      }

      val task: Task = getTask(name)
      task.startWork()
      currentTask = Option(task)

      task
   }

   def stopTask(name: String) = {
      if (!taskExists(name)) {
         throw new TaskNotFoundException(name)
      }
      if (!currentTask.isDefined) {
         throw new NoTaskStartedException()
      }
      if (currentTask.isDefined && !currentTask.get.equals(getTask(name))) {
         throw new TaskStoppedException(name)
      }

      val task: Task = getTask(name)
      task.stopWork()
      currentTask = Option(null)

      task
   }

   def stopAllTasksIfAny() {
      if (currentTask.isDefined) {
         currentTask.get.stopWork()
         currentTask = Option(null)
      }
   }

   def countTotalTime() = {
      tasks.foldLeft(0L)((count, task) => count + task.totalTime)
   }

   /*
   def assignTaskToUser(task: Task, user: User) = {
      user.assignTask(task)
   }

   def removeTaskFromUser(task: Task, user: User) = {
      user.removeTask(task)
   }
   */

   /**
    * Check whether task with such name already exists
    * @param name name of task
    * @return true if task exists, false otherwise
    */
   def taskExists(name: String) = {
      tasks.exists((t: Task) => t.name.equals(name))
   }

   def getTask(name: String): Task = {
      val task: Option[Task] = tasks.find((t: Task) => t.name.equals(name))
      if (!task.isDefined) {
         throw new TaskNotFoundException(name)
      }
      else {
         task.get
      }
   }
}

/**
 * Generic task exception
 */
class TaskException extends Exception {

}

/**
 * Thrown if task that is being added already exists
 * @param taskName
 */
class TaskAlreadyExistsException(val taskName: String) extends TaskException {
   override def getMessage = {
      "Task '" + taskName + "' already exists"
   }
}

class TaskNotFoundException(val taskName: String) extends TaskException {
   override def getMessage = {
      "Task '" + taskName + "' does not exist"
   }
}

class TaskAlreadyStartedException(val taskName: String) extends TaskException {
   override def getMessage = {
      "Task '" + taskName + "' already started"
   }
}

class TaskStoppedException(val taskName: String) extends TaskException {
   override def getMessage = {
      "Task '" + taskName + "' is stopped"
   }
}

class NoTaskStartedException() extends TaskException {
   override def getMessage = {
      "No task is started"
   }
}