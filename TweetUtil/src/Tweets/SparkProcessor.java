package Tweets;



import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.lang.Iterable;

import scala.Tuple2;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

public class SparkProcessor {
	public static void SparkProcessWord(String keys[],String infile,int length){
		String inputFile = infile;
		String outputFile = "salida";
		SparkProcessor.deleteDirectory(outputFile);
		File f=new File(outputFile);
		f.delete();//Borramos el directorio en el caso de existir
		/*String keys[]={"Telefonica","Telefónica","Abertis","Acciona","Acerinox","Aena",
				 "Amadeus","Arcelor Mital","Sabadell","Santander"
				 ,"Bankia","Bankinter","CaixaBank","Cellnex","Enagas","Endesa","Ferrovial",
				 "Gamesa","Gas Natural","Grifols","Iberdrola","Inditex","Indra"
				 ,"Mapfre","Mediaset","Red Eléctica Española","Repsol","Viscofan"};*/
		// Create a Java Spark Context.
		
		SparkConf conf = new SparkConf().setAppName("wordCount").setMaster("local[1]");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		
		// Load our input data.
		JavaRDD<String> input = sc.textFile(inputFile);
		// Split up into words.
		
		JavaPairRDD<String, String> tweets = input.mapToPair(new PairFunction<String, String, String>() {

			@Override
			public Tuple2<String, String> call(String arg0) throws Exception {
				
				return null;
			}
		});
		
		
		JavaRDD<String> words = input.flatMap(new FlatMapFunction<String, String>() {
			public Iterator<String> call(String x) {
				//x=x.replace("-", " ");
				x=x.replace(";", " ");
				x=x.replace(",", " ");
				x=x.replace("\"", " ");
				x=x.replace("/", " ");
				x=x.replace(".", " ");
				x=x.replace("#", " ");
				x=x.replace("!", " ");
				x=x.replace("¡", " ");
				x=x.replace("¿", " ");
				x=x.replace("?", " ");
				x=x.replace("=", " ");
				x=x.replace("'", " ");
				x=x.replace("_", " ");
				x=x.replace("@", " ");
				x=x.replace(":", " ");
				x=x.replace("(", " ");
				x=x.replace(")", " ");
				x=x.replace("…", " ");
				x=x.replace("]", " ");
				x=x.replace("[", " ");
				
				
				x=x.toLowerCase();
			/*	if(!SparkProcessor.filter(x, keys)){
					x="Useless";
				}
				*/
				String selected[]=x.split(" ");
				for(int i=0;i<selected.length;i++){
					if(!SparkProcessor.filter(selected[i], keys,length)){
						selected[i]="useless";
					}
				}
				
				return Arrays.asList(selected).iterator();
			}
		});
		// Transform into word and count.
				JavaPairRDD<String, Integer> counts = words.mapToPair(new PairFunction<String, String, Integer>() {
					public Tuple2<String, Integer> call(String x) {
						x.toLowerCase();
						return new Tuple2(x, 1);
					} // List<Obj<String, int>>
				}).reduceByKey(new Function2<Integer, Integer, Integer>() {
					public Integer call(Integer x, Integer y) {
						return x + y;
					}
				});
		
		
		
		// Save the word count back out to a text file, causing evaluation.
		counts.saveAsTextFile(outputFile);
	}

	static boolean filter(String in, String filter[], int length) {
		boolean aux = false;
		if (in.length() >= length) {
			if (in.equals("") || in.contains("http")) {
				aux = false;
			} else {
				if (filter.length == 0) {
					aux = true;
				} else {
					for (String sr : filter) {
						if (sr.equalsIgnoreCase(in)) {
							aux = true;
							break;
						}
					}

				}
			}
		}else{
			aux=false;
		}
		return aux;

	}
	
	
	private static void deleteDirectory(String output){
		File f= new File(output);
		if(f.isDirectory()){
			File files[]=f.listFiles();
			for(File aux:files){
				aux.delete();
			}
		}
		f.delete();
		
	}
	
}


