import shapes._

ShapeDrawingActor.start()

ShapeDrawingActor ! new Circle(new Point(0.0,0.0), 1.0)
ShapeDrawingActor ! new Rectangle(new Point(0.0,0.0), 1.0, 2.0)
ShapeDrawingActor ! new Triangle(new Point(0.0,0.0), new Point(1.0,3.0), 
	new Point(1.0,0.0))

ShapeDrawingActor ! 42
ShapeDrawingActor ! "exit"