package liftactorfutures

import net.liftweb.actor.LiftActor

/**
 * @author Nikolay
 * Date: 12/26/11
 * Time: 3:43 PM
 */
object FutureInterleavingTest extends App {

  def log(msg: Any) = println("[" + Thread.currentThread.getName + "] " + msg)

  object FutureActor extends LiftActor {
    def messageHandler: PartialFunction[Any, Unit] = {
      case x: Int =>
        Thread.sleep(20)
        log("futureactor responding")
        reply(x * x)
    }
  }

  log("Application starting")

  val future = FutureActor !< 42

  future foreach { result =>
    log("handler A enter for " + result)
    Thread.sleep(200)
    log("handler A leave")
  }

  Thread.sleep(100)

  future foreach { result =>
    log("handler B enter for " + result)
    Thread.sleep(200)
    log("handler B leave")
  }

}