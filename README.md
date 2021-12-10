### Open port 9999 and send data when run project

```
nc -l 9999
```

### map function

```
mvn clean package
spark-submit --class spark.streaming.map.Main target/SparkStreamingOperation-0.0.1-SNAPSHOT.jar
```

### flatMap function

```
mvn clean package
spark-submit --class spark.streaming.flatmap.Main target/SparkStreamingOperation-0.0.1-SNAPSHOT.jar
```

### filter function

```
mvn clean package
spark-submit --class spark.streaming.filter.Main target/SparkStreamingOperation-0.0.1-SNAPSHOT.jar
```