import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.duration._
import scala.concurrent.Await

def FunToThread(f: () => Unit) = new Thread(() => f())

def PrintTest(): Unit = {
  val test = FunToThread(() => println("Dette er en kul test"))
  test.start()
}

private var counter: Int = 0
def increaseCounter(): Unit = {
counter += 1
}

def printCounter(): Unit = {
  println(counter)
}


// This phenomenon is called the race condition, and occurs when two or more threads acess a shared variable,
// where the output of the program is dependent on the timing at which the separate threads got executed. We have illustrated a situation where
// two threads are changing the state of a shared variable, and one reads it, but threre is no sync between them, meaning they execute there statements
// at a non sequential manner, and the output is not reliable. So any situation where we read and write a shared variable, we need "atomocity".

val atomicCounter = new AtomicInteger(0)
def increaseCounterSafe(): Unit = this.synchronized {
atomicCounter.incrementAndGet()
}

def printCounterSafe(): Unit = this.synchronized {
  println(atomicCounter)
}

val thread1 = FunToThread(increaseCounter)
val thread2 = FunToThread(increaseCounter)
val thread3 = FunToThread(printCounter)

def tester(): Unit = {
  thread1.start()
  thread2.start()
  thread3.start()
}

val threadS1 = FunToThread(increaseCounterSafe)
val threadS2 = FunToThread(increaseCounterSafe)
val threadS3 = FunToThread(printCounterSafe)

def testerSafe(): Unit = {
  threadS1.start()
  threadS2.start()
  threadS3.start()
}

PrintTest()

tester()

testerSafe()

// Task2d
// A deadlock in concurrency occurs when multiple values are being accessed, but a collision occurs, making all of them wait for each other (or itself).
// This results in none of them taking any action and thus creating a deadlock.

object deadLObj {
  lazy val initialState = 42
  lazy val start = deadLObjTwo.initialState
}

object deadLObjTwo {
  lazy val initialState = deadLObj.initialState
}

def CheckDeadlock: Unit = {
    val result = Future.sequence(Seq(
      Future {
        deadLObj.start
      },
      Future {
        deadLObjTwo.initialState
      }
    ))
    Await.result(result, 5.second)
  }

  CheckDeadlock
