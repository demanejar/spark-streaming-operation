package spark.streaming.flatmap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("Spark Streaming Demo");
		try (JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(1))) {
			JavaReceiverInputDStream<String> input = jsc.socketTextStream("localhost", 9999);
			JavaDStream<String> words = input.flatMap(new FlatMapFunction<String, String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public Iterator<String> call(String value) throws Exception {
					List<String> listString = Arrays.asList(value.split(" "));
					return listString.iterator();
				}
			});
			
			words.print();
			
			
			jsc.start();
			jsc.awaitTermination();
		}
	}
}
