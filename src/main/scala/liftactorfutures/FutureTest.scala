package liftactorfutures

import net.liftweb.actor.LiftActor

/**
 * @author Nikolay
 * Date: 12/26/11
 * Time: 3:09 PM
 */
object FutureTest extends App {
  
  def log(msg: Any) = println("[" + Thread.currentThread.getName + "] " + msg)

  object ServiceActor extends LiftActor {
    override def messageHandler = {
      case x: Int => log("Service enter"); reply(x*x); log("Service leave")
    }
  }

  case object Go

  object ClientActor extends LiftActor {
    override def messageHandler: PartialFunction[Any, Unit] = {
      case Go =>
        log("Hello world")
        val future = ServiceActor !< 42
        future foreach { result =>
          log("future got " + result)
          ClientActor ! result
          Thread.sleep(1000)
          log("future exit")
        }
      case x: Int => log("client got " + x)
    }
  }

  ClientActor ! Go

}