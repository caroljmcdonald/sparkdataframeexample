package examples

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
 * 
 */
object App {

  def main(args : Array[String]) {
    val conf = new SparkConf().setMaster("local[1]")
      .setAppName("Test app")

    val sc = new SparkContext(conf)

    val col = sc.parallelize(0 to 100 by 5)
    val smp = col.sample(true, 4)
    val colCount = col.count
    val smpCount = smp.count
    
    println("orig count = " + colCount)
    println("sampled count = " + smpCount)
  }

}
