package spark.streaming.map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("Spark Streaming Demo");
		try (JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(1))) {
			JavaReceiverInputDStream<String> input = jsc.socketTextStream("localhost", 9999);
			JavaDStream<String> upcase = input.map(new Function<String, String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String call(String value) throws Exception {
					return value.toUpperCase();
				} 
			});
			
			upcase.print();
			
			
			jsc.start();
			jsc.awaitTermination();
		}
	}
}
