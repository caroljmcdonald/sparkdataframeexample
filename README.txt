
These are a couple of example Spark DataFrames applications, written in Scala, to demonstrate How to get started with Spark DataFrames on a MapR sandbox (requires Spark 1.3 )

There are  2 datafiles  in this directory :
	ebay.csv  
	sfpd.csv  
You will need to copy these files to your MapR sandbox, or wherever you have Spark installed.

You can run these examples in the spark shell by putting the code from the scala classees in the spark shell after launching:
 
$spark-shell --packages com.databricks:spark-csv_2.10:1.0.3

Or you can run the applications with these steps:

Step 1: First compile the project: Select project  -> Run As -> Maven Install

Step 2: Copy the sparkdataframesapp-1.0.jar to the sandbox 

To run the  standalone :

spark-submit --class SparkDFebay --master yarn sparkdataframesapp-1.0.jar

spark-submit --class SparkDFsfpd --master yarn --packages com.databricks:spark-csv_2.10:1.0.3 sparkdataframesapp-1.0.jar
