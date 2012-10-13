package shapes

import actors.Actor

/**
 * @author yozh
 */
object ShapeDrawingActor extends Actor {
   def act() {
      loop {
         receive {
            case s: Shape => s.draw()
            case "exit" => println("exiting..."); exit
            case x: Any => println("Unknown message: " + x)
         }
      }
   }
}
