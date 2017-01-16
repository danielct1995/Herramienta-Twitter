package Tweets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Grapher {

	public Grapher() {
		DefaultPieDataset data = new DefaultPieDataset();
		try {
			List<Element> elements = this.readFile();
			this.calculateStats(elements);
			for (Element e : elements) {
				if (!e.getKey().equalsIgnoreCase("useless")) {
					data.setValue(e.getKey(), e.getStats());
				}
			}
			   // Creando el Grafico
	        JFreeChart chart = ChartFactory.createPieChart("Gráfico de los datos",data);
	 
	        // Mostrar Grafico
	        ChartFrame frame = new ChartFrame("JFreeChart", chart);
	        frame.pack();
	        frame.setVisible(true);	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR - No se detectó el archivo de entrada");
			System.exit(0);
		}
		
		
		
		
		
	}
	
	
	public Grapher(int limit) {
		DefaultPieDataset data = new DefaultPieDataset();
		try {
			List<Element> elements = this.readFile();
			elements=this.getMostMencionedList(elements, limit);
			
			this.calculateStats(elements);
			System.out.println("\nMÁS MENCIONADOS\n");
			for (Element e : elements) {
				if (!e.getKey().equalsIgnoreCase("useless")) {
					data.setValue(e.getKey(), e.getStats());
					System.out.println(e.getKey()+": "+e.getValue());
				}
			}
			   // Creando el Grafico
	        JFreeChart chart = ChartFactory.createPieChart("Gráfico de los datos",data);
	 
	        // Mostrar Grafico
	        ChartFrame frame = new ChartFrame("JFreeChart", chart);
	        frame.pack();
	        frame.setVisible(true);	
	        
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR - No se detectó el archivo de entrada");
			System.exit(0);
		}
		
		
	}
	
	
	
	
	
	
	
	
	private List <Element> getMostMencionedList(List<Element> in, int size){
		List<Element> list= new LinkedList<Element>();
		int left =size;
		Element max=null;;
		while(left!=0 || in.size()==0){
			for(Element e: in){
				if(max==null){
					max=e;
				}else{
					if(e.getValue()>max.getValue()){
						max=e;
					}
				}
			}
			left--;
			list.add(max);
			in.remove(max);
			max=null;
		}
		in.clear();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("finally")
	private List <Element> readFile() throws FileNotFoundException, IOException{
		System.out.println("RESULTADOS ABSOLUTOS:");
		LinkedList<Element> list =new LinkedList<Element>();
		try(BufferedReader bf= new BufferedReader(new FileReader("salida/part-00000"))){
			String read;
			
			while(true){
				read=bf.readLine();
				
				
				if(read!=null){
					System.out.println(read);
					String key = read.substring(read.indexOf('(')+1, read.indexOf(','));
					int value = Integer.parseInt(read.substring(read.indexOf(',')+1,read.indexOf(')')));
					Element a = new Element();
					a.setKey(key);
					a.setValue(value);
					list.add(a);
				}else{
					break;
				}
				
			}
			
			
		}finally{
			return list;
		}
		
		
	}
	private void calculateStats(List <Element> list){
		int total=0;
		for(Element e: list){
			if(!e.getKey().equalsIgnoreCase("useless")){
				total=total+e.getValue();	
			}
		}
		
		
		for(Element e:list){
		
			float st;
			if(!e.getKey().equalsIgnoreCase("useless")){
				st=(float)e.getValue()/total;
				e.setStats(st);	
				}
		}
	}
	
	

}
