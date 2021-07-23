import scala.io.Source
object ScalawordCount {
  def main(args: Array[String]): Unit = {
    val sentence = scala.io.StdIn.readLine("Enter the string: ")
    val wordCount = scala.collection.mutable.Map[String, Int]()
    for (word <- sentence.split(" ")) {
      var words=word.toLowerCase()
      wordCount(words) = if (wordCount.contains(words)) wordCount(words) + 1 else 1
    }
    for ((k, v) <- wordCount)
      printf("Word %s occurs %d times\n", k, v)
    println(wordCount)
  }
}
