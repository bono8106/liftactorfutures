package liftactorfutures

import net.liftweb.actor.LiftActor

object BasicTest extends App {
  
  case object Message

  object HelloActor extends LiftActor {
    override def messageHandler: PartialFunction[Any, Unit] = {
      case Message => println("Hello world")
    }
  }

  HelloActor ! Message

}
