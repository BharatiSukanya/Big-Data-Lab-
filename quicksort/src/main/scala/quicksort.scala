object quicksort {
  def quicksortFunctional(a: Array[Int]): Array[Int] =
    if (a.length <= 1) a
    else quicksortFunctional(a.filter(a(0)>_))++(a.filter(a(0)==_))++
      quicksortFunctional(a.filter(a(0)<_))

  def quicksortImperative(a: Array[Int]) {
    def swap(i: Int, j: Int) {
      val t = a(i)
      a(i) = a(j)
      a(j) = t
    }
    def sort(l: Int, r: Int) {
      val pivot = a((l + r) / 2)
      var i = l
      var j = r
      while (i <= j) {
        while (a(i) < pivot) i += 1
        while (a(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j) sort(l, j)
      if (j < r) sort(i, r)
    }
    sort(0, a.length - 1)
  }

  def main(args: Array[String]) {
    val r=scala.util.Random
    var choice=0
    while(choice!=3) {
      println("\n\n\tQuickSort:")
      println("1) QuickSort using Fuctional Programming")
      println("2) QuickSort using Impertive Programming")
      println("3) Quit")
      print("Enter Your Choice : ")
      choice=scala.io.StdIn.readInt()
      choice match {
        case 1 => {
          println("\n\tQuickSort Functional : ")
          var a:Array[Int] = (for(i <- 1 to 10) yield r.nextInt(1000)).toArray
          println("Before Sorting : "+a.mkString(" "))
          a = quicksortFunctional(a)
          println("After Sorting : "+a.mkString(" "))
        }
        case 2 => {
          println("\n\tQuickSort Imperative : ")
          var b:Array[Int] = (for(i <- 1 to 10) yield r.nextInt(1000)).toArray
          println("Before Sorting : "+b.mkString(" "))
          quicksortImperative(b)
          println("After Sorting : "+b.mkString(" "))
        }
        case 3 => println("\t...Quiting...")
        case _ => println("\t!!!Invalid Choice!!!")
      }
    }
  }
}
