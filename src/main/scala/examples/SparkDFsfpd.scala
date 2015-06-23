/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import com.databricks.spark.csv

//define the schema using a case class
case class Sfpd(auctionid: String, bid: Float, bidtime: Float, bidder: String, bidderrate: Integer, openbid: Float, price: Float, item: String, daystolive: Integer)

object SparkDFsfpd {
  def main(args: Array[String]) {
    
    // set local for debugging
      
    val conf = new SparkConf().setAppName("SparkDFsfpd")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    import sqlContext.implicits._
    import sqlContext._
    import org.apache.spark.sql._

    // load the data into an RDD
    val df = sqlContext.load("com.databricks.spark.csv", Map("path" -> "/user/user01/sfpd.csv", "header" -> "true"))

    df.select("Category").distinct.collect().foreach(println)

    // register as a temp table 
    df.registerTempTable("sfpd")
    // now you can use sql 
    sqlContext.sql("SELECT distinct Category FROM sfpd").collect().foreach(println)

    // top 10 Resolutions 
    sqlContext.sql("SELECT Resolution , count(Resolution) as rescount FROM sfpd group by Resolution order by rescount desc limit 10").collect().foreach(println)
    val t = sqlContext.sql("SELECT Category , count(Category) as catcount FROM sfpd group by Category order by catcount desc limit 10")
 
    t.show()
    t.map(t => "column 0: " + t(0)).collect().foreach(println)
  }
}
