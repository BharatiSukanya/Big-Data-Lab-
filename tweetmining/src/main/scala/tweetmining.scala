import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd._
object tweetmining {
  val conf = new SparkConf()
    .setAppName("tweetminingSpark")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)
  var pathToFile = "C:\\Users\\Sukanya Bharati\\Desktop\\tweetmining\\src\\main\\scala\\rt.json"

  def main(args: Array[String]) {
    if (args.length != 1) {
      println()
      println("Dude, I need exactly one argument.")
      println("But you have given me " + args.length + ".")
      println("The argument should be path to json file containing a bunch of tweets. esired.")
      System.exit(1)
    }
    pathToFile = args(0)
    val tweets =
      sc.textFile(pathToFile).mapPartitions(TweetUtils.parseFromJson(_))

    val tweetsByUser=tweets.map(x=>(x.user,x)).groupByKey()

    val numTweetsByUser=tweetsByUser.map(x=>(x._1,x._2.size))

    val sortedUsersByNumTweets=numTweetsByUser.sortBy(_._2,ascending = false)

    sortedUsersByNumTweets.take(10).foreach(println)

    val selectedTweets=sc.parallelize(sortedUsersByNumTweets.take(10))
    selectedTweets.saveAsTextFile("tweetsDir")

  }
}
import com.google.gson._

object TweetUtils {
  case class Tweet (
                     id : String,
                     user : String,
                     text : String,
                     place : String,
                     country : String,
                     lang : String
                   )
  def parseFromJson(lines:Iterator[String]):Iterator[Tweet] = {
    val gson = new Gson
    lines.map(line => gson.fromJson(line, classOf[Tweet]))
  }
}
