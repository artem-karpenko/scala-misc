import shapes.{Rectangle, Point, Circle, ShapeDrawingActor}

ShapeDrawingActor.start()

ShapeDrawingActor ! new Circle(new Point(2, 3), 2.5)
ShapeDrawingActor ! "hello!"
ShapeDrawingActor ! new Rectangle(new Point(5, 5), 10, 20)
ShapeDrawingActor ! 42
ShapeDrawingActor ! "exit"