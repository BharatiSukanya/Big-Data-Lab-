import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
object IPL {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Sum").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("ipl").getOrCreate()

    val pathToMatchFile = "C:\\Users\\Sukanya Bharati\\Desktop\\bdtselfstudy\\src\\main\\scala\\bdt\\Match.csv"
    // The Match.csv file has the toss won/match won data for every game.

    // Read the file into a dataframe
    val matchDf = spark.read.format("csv").option("sep",",").option("header",true).option("inferSchema",true).load(pathToMatchFile)

    matchDf.createOrReplaceTempView("matchStats")

    // finding  the total number of entries in the table which is equal to number of matches
    val N = spark.sql("SELECT COUNT(*) FROM matchStats").first()(0).asInstanceOf[Long]

    //entries where the toss winner is also the match winner
    val tossNMatchWinnersDF = spark.sql("SELECT * FROM matchStats WHERE Toss_Winner_Id = Match_Winner_Id")
    tossNMatchWinnersDF.createOrReplaceTempView("tossNMatchWinners")

    // we find the count of entries in the table
    val M = spark.sql("SELECT COUNT(*) FROM tossNMatchwinners").first()(0).asInstanceOf[Long]
    println(M)

    //now printing it as a percentage
    println("Percentage of times Toss Winners have won the match = " + (M*100.0)/N + "%")

  }
}
