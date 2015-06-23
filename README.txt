Commands to run labs:

Step 1: First compile the project: Select project 'lab-exercises' -> Run As -> Maven Install

Step 2: Copy the lab-exercises-1.0.jar to the cluster

To run the  lab:
spark-submit --class SparkWordCount --master yarn sparkdataframesapp-1.0.jar /user/user01/input/alice.txt /user/user01/output

spark-submit --class SparkDFebay --master yarn sparkdataframesapp-1.0.jar

spark-submit --class SparkDFsfpd --master yarn --packages com.databricks:spark-csv_2.10:1.0.3 sparkdataframesapp-1.0.jar