package shapes

import scala.actors._
import scala.actors.Actor._

object ShapeDrawingActor extends Actor {
	def act() {
		loop {
			receive {
				case s: Shape 	=> s.draw()
				case "exit" 	=> println("Exit..."); exit
				case x: Any		=> println("Error: unknown message: " + x)
			}
		}
	}
}