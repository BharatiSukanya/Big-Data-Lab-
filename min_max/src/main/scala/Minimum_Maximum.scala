import scala.collection.mutable.ArrayBuffer;
object Minimum_Maximum {
  def main(args: Array[String]): Unit = {
    var numArray = new ArrayBuffer[Int]()
    println("Enter number of elements")
    val n = scala.io.StdIn.readInt()		//Read the number of items in Array
    println("Enter elements")
    for (i <- 1 to n)				//Read the array elements
      numArray += scala.io.StdIn.readInt()
    println("The elements of array are")
    println(numArray)
    val result = minmax(numArray) 				// Returned value will be tuple
    println("Maximum number is " + result._1)			//Display the maximum
    println("Minimum number is " + result._2)			//Display the minimum
  }

  def minmax(numArray:ArrayBuffer[Int]): (Int,Int) = {

    var min:Int = 999		//Initialize minumum and maximum
    var max:Int = -999
    for(n<-numArray){
      if(n<min)
        min=n
      if(n>max)
        max=n
    }
    (max,min)					//Return mx and mn as items of tuple
  }
}
