import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
object ipl_dataset {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Sum").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("ipl").getOrCreate()

    val pathToMatchFile = "C:\\bdt\\Match.csv"
    val matchDf = spark.read.format("csv").option("sep",",").option("header",true).option("inferSchema",true).load(pathToMatchFile)
    matchDf.createOrReplaceTempView("matchStats")
    //    val N = spark.sql("SELECT COUNT(*) FROM matchStats").first()(0).asInstanceOf[Long]
    //    println(N)
    val N = spark.sql("SELECT COUNT(*) FROM matchStats").first()(0).asInstanceOf[Int]
    val tossNMatchWinnersDF = spark.sql("SELECT * FROM matchStats WHERE Toss_Winner_Id = Match_Winner_Id")
    //    println(tossNMatchWinnersDF)
    tossNMatchWinnersDF.createOrReplaceTempView("tossNMatchWinners")
    val M = spark.sql("SELECT COUNT(*) FROM tossNMatchwinners").first()(0).asInstanceOf[Int]
    println(M)
    println("Percentage of times Toss Winners have won the match = " + (M*100.0)/N + "%")
  }
}
