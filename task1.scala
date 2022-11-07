object Task1 extends App {
	println("Hello World")

	// Task 1a
	val Array = for (i <- 0 until 50) yield i
	println(Array)
	
  	// Task 1b
	val Arr = List(5, 10, 15, 20, 25, 30, 35, 40, 45, 50);
	var Sum = 0
	for (i <- Arr) { 
    		Sum += i
  	}
	println(Sum)

  	// Task 1c
	def sum(x: List[Int]): Int = {
		if(x.isEmpty)
			return 0
		else
			return x.head + sum(x.tail)
	}
	println(sum(Arr))

  	// Task 1d
	def fibonacci(nth: Int): BigInt = {	// F_n = F_(n-1) + F_(n-2)
		def fibRec(n: Int, F_1: BigInt, F_2: BigInt): BigInt = n match {
			case 0 => F_1
			case 1 => F_2
			case _ => fibRec(n - 1, F_2, F_1 + F_2)
		}
		fibRec(nth + 1, 0, 1) // Plus one since the fibonacci sequence starts at index 1
	}
	println(fibonacci(4))
  
  // The BigInt data type is intended for use when integer values might exceed the range that is supported by the int data type.
  // Int supports values ranging from 32-bit two’s complement integer (-2^31 to 2^31-1, inclusive) -2,147,483,648 to 2,147,483,647
}
