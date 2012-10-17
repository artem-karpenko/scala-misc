package org.yozh.scala.misc

abstract class Widget

class Button(val label: String) extends Widget {
   def click() = {
      // ...
   }
}

trait Subject {
   type Observer = { def receiveUpdate(subject: Any) }

   private var observers = List[Observer]()
   def addObserver(observer: Observer) =  observers ::= observer
   def notifyObservers() = observers foreach(_.receiveUpdate(this))
}

class ObservableButton(name: String) extends Button(name) with Subject {
   override def click() = {
      super.click()
      notifyObservers()
   }
}

class ButtonCountObserver {
   var count = 0
   def receiveUpdate(subject: Any) = count += 1
}

object Test {
   def main(args: Array[String]) {
      var btn = new ObservableButton("OK")
      val observer: ButtonCountObserver = new ButtonCountObserver
      btn.addObserver(observer)

      btn.click()
      btn.click()
      btn.click()

      println(observer.count)
   }
}