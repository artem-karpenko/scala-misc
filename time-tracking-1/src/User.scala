import collection.mutable

/**
 * @author Artem
 */
class User(val login: String, val password: String) {
   val tasks: collection.mutable.Set[Task] = new collection.mutable.HashSet[Task]
   val timeSpentPerTask: collection.mutable.Map[Task, Long] = new mutable.HashMap[Task, Long]

   def assignTask(task: Task) = {
      tasks.add(task)
   }

   def removeTask(task: Task) = {
      tasks.remove(task)
   }

   def logTime(task: Task, time: Long) = {
      if (!timeSpentPerTask.contains(task)) {
         timeSpentPerTask.put(task, 0)
      }
      timeSpentPerTask.put(task, timeSpentPerTask.get(task).get + time)
   }
}
