import org.joda.time.Duration

/**
 * @author Artem
 */
class Task(val name: String) {
   var startTime = -1L
   var workInProgress = false
   var totalTime = 0L

   /**
    * Start work on this task
    * @return true if work was started, false otherwise
    */
   def startWork() = {
      var result = false
      if (!workInProgress) {
         startTime = System.currentTimeMillis()
         workInProgress = true
         result = true
      }
      result
   }

   /**
    * Stop work on this task
    * @return true if work was stopped, false otherwise
    */
   def stopWork() = {
      var result = false
      if (workInProgress) {
         val stopTime = System.currentTimeMillis()
         workInProgress = false

         val timeWorked = stopTime - startTime
         totalTime += timeWorked
      }
      result
   }

   override def toString = {
      name
   }
}

object EmptyTask extends Task("") {

}