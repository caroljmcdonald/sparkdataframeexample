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

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql._

//define the schema using a case class
case class Auction(auctionid: String, bid: Float, bidtime: Float, bidder: String, bidderrate: Integer, openbid: Float, price: Float, item: String, daystolive: Integer)

object SparkDFebay {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SparkDFebay")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    import sqlContext.implicits._
    import sqlContext._

    // load the data into an RDD
    val ebayText = sc.textFile("/user/user01/ebay.csv")

    // create an RDD of Auction objects 
    val ebay = ebayText.map(_.split(",")).map(p => Auction(p(0), p(1).toFloat, p(2).toFloat, p(3), p(4).toInt, p(5).toFloat, p(6).toFloat, p(7), p(8).toInt))

    // change ebay RDD of Auction objects to a DataFrame
    val auction = ebay.toDF()
    // How many auctions were held ? 
    val count = auction.select("auctionid").distinct.count
    System.out.println(count)
    


  }
}
