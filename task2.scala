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