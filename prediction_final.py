import pandas as pd
from pyspark.ml.feature import StringIndexer, OneHotEncoder, VectorAssembler, StandardScaler
from pyspark.ml import Pipeline
from pyspark.ml.classification import LogisticRegression
from pyspark.ml.regression import LinearRegression
from pyspark.ml.evaluation import MulticlassClassificationEvaluator, RegressionEvaluator
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.conf import SparkConf
from pyspark.context import SparkContext
from pyspark.sql.context import SQLContext
from pyspark.python.pyspark.shell import sqlContext
from pyspark.sql.session import SparkSession
from pyspark.sql.functions import isnan, when, count, col, isnull, year, month, dayofmonth, hour, dayofweek,\
    ceil
from pyspark.sql.functions import abs
from pyspark.mllib.regression import RidgeRegressionWithSGD
from pyspark.sql.types import IntegerType, StructType, StructField, StringType
import logging


logger = logging.getLogger( __name__ )

# %%
spark = SparkSession.builder.appName("test").master("local[*]").getOrCreate()
test = spark.read.csv( "D:\비트교육센터\Workspace\Seoul - 20230228\log\logfile4.log", header=True, inferSchema=True )
print("-------------")


# %%
test.head()


# %%
from pyspark.sql.functions import to_timestamp

# 'time' 열에 있는 데이터를 datetime으로 변환하여 새로운 열 생성
test = test.withColumn("datetime_time", to_timestamp(test["time"], "yyyy-MM-dd HH:mm"))
test = test.drop("time")

# %%

test1 = test.withColumn("days", test.datetime_time)
test1 = test1.withColumn("time", test.datetime_time)
test.head()

# %%
# 날짜 및 시간 추가

dateDf = test1.select("name", "min", "max", dayofweek(test1["days"]).alias("days"), hour(test1["time"]).alias("time"))
test2 = dateDf.withColumn("day", dateDf.days -1)
real_dateDf = test2.select("name", "min", "max", "day", "time")
test.head()

# %%
# min max 평균값 구하기

pop_df = real_dateDf.withColumn("population", (real_dateDf.min + real_dateDf.max)/2)


# %%
# 이름 자료를 0, 1, 2로 수정
stridx = StringIndexer( inputCol="name", outputCol="name_num" )
stridx2 = StringIndexer( inputCol="population", outputCol="population_idx" )

# %%
# 데이터 이름 벡터 어셈블러에 넣기 위해 그룹화.
inputs = ["day", "time", "population"]


# %%
# 데이터 이름을 묶어주기
va = VectorAssembler( inputCols=inputs, outputCol="features" )

# 파이프라인으로 묶어주기
pipeline = Pipeline( stages=[ va, stridx ] )
# 파이프라인 fit으로 학습시키기
pipelineModel = pipeline.fit( pop_df )
datadf = pipelineModel.transform( pop_df )


# %%
inputs2 = ["name_num", "day", "time", "min", "max"] 

va2 = VectorAssembler( inputCols=inputs2, outputCol="features2" )

pipeline2 = Pipeline( stages=[ va2 ] )
# 파이프라인 fit으로 학습시키기
pipelineModel2 = pipeline2.fit( datadf )
datadf2 = pipelineModel2.transform( datadf )




# 여기서 분석하기 위해서는 features, label만 남기고 앞에 자료는 날린다.
datadf2 = pipelineModel2.transform( datadf ) 
datadf2_test = datadf2.drop('population')

# %%
rg = LinearRegression(featuresCol="features2", labelCol="population", maxIter=100, regParam=0.1, elasticNetParam=0.0)

model = rg.fit( datadf2 )
predicts = model.transform( datadf2 )


# %%
pred_evaluator = RegressionEvaluator(predictionCol="prediction", labelCol='population', metricName="r2")
pred_test = pred_evaluator.evaluate(predicts)


final_df = predicts.select("name", "day", "time", "prediction")


place_name_list=["창덕궁·종묘", "광화문·덕수궁", "경복궁·서촌마을",
                 "서울숲공원", "남산공원", "국립중앙박물관·용산가족공원", "서울대공원", "이촌한강공원", "월드컵공원", "잠실종합운동장", "반포한강공원", "잠실한강공원", "뚝섬한강공원", "망원한강공원", "북서울꿈의숲",
                 "종로·청계 관광특구", "이태원 관광특구", "잠실 관광특구", "명동 관광특구", "동대문 관광특구", "강남 MICE 관광특구", "홍대 관광특구",
                 "여의도", "인사동·익선동", "DMC(디지털미디어시티)", "북촌한옥마을", "성수카페거리", "가로수길", "압구정로데오거리", "창동 신경제 중심지", "영등포 타임스퀘어", "노량진", "쌍문동 맛집거리", "수유리 먹자골목", "낙산공원·이화마을",
                 "구로디지털단지역", "선릉역", "서울역", "가산디지털단지역", "역삼역", "강남역", "고속터미널역", "용산역", "교대역", "연신내역", "신촌·이대역", "왕십리역", "신림역", "건대입구역", "신도림역"]
#place_name_list = ["강남역", "서울역"]


schema = StructType([StructField("name", StringType(), True), StructField("day", IntegerType(), True), StructField("time", IntegerType(), True), StructField("prediction", IntegerType(), True)])

spark_1 = SparkSession.builder.appName('empty_df1').getOrCreate()
spark_2 = SparkSession.builder.appName('empty_df2').getOrCreate()
emp_RDD = spark_1.sparkContext.emptyRDD() 

spark_df1 = spark_1.createDataFrame( [] , schema)
spark_df2 = spark_2.createDataFrame( [] , schema)


# %%

# print(spark_df1.printSchema())
# print(spark_df1.show())

new_list = []
zz=1
for i in range(len(place_name_list)):
    for j in range(7) :
        for k in range(24) :
            # new_list.append( final_df.filter( (col('name')==place_name_list[i]) & (col('day')==j) & (col('time')==k) ).show() )
            new_list_1 = final_df.filter( (col('name')==place_name_list[i]) & (col('day')==j) & (col('time')==k) )
            new_list_1 = new_list_1.groupBy('name', 'day', 'time').avg('prediction') 
            new_list_1 = new_list_1.withColumnRenamed('avg(prediction)', 'prediction')
            new_list_1 = new_list_1.withColumn('prediction', new_list_1['prediction'].cast(IntegerType()))
            new_list_1 = new_list_1.withColumn('id', ((new_list_1['day']*0)+zz))
            new_list_1 = new_list_1.select('id', 'name', 'day', 'time', 'prediction')
            
            # # PySpark DataFrame을 로컬 Pandas DataFrame으로 변환
            local_df = new_list_1.toPandas()

            # # Pandas DataFrame을 CSV 파일로 저장
            local_df.to_csv("D:/비트교육센터/Workspace/Seoul - 20230228/log/logfiletest.csv", index=False, mode='a', header=False)

            
            zz += 1
            # spark_df2.union(spark_df1.union(new_list_1))         
        
spark_df2.show()


