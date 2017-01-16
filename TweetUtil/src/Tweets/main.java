package Tweets;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 0) {
			switch (args[0]) {
			case "--dataset":
				System.out.println("Creando Streaming dataset");
				String search[] = new String[args.length - 2];
				if (args.length > 1) {
					String filename = args[1];

					if (args.length > 2) {
						for (int i = 2; i < args.length; i++) {
							search[i - 2] = args[i];
						}
						Searcher.Searcher(search,filename);
					} else {
						System.out.println("Introduce algún tértmino de búsqueda");
					}
				}else {System.out.println("Introduzca nombre de archivo y argumentos");}
				break;

			case "--sparkWord":
				System.out.println("Proceso Spark");
				String keys[] = new String[args.length - 2];
				if (args.length > 1) {
					String file =args[1];
					
					for (int i = 2; i < args.length; i++) {
						keys[i - 2] = args[i];
					}
					SparkProcessor.SparkProcessWord(keys,file,0);
					if(args.length>2){
						new Grapher();
						
					}
				}else {System.out.println("Introduzca nombre de archivo y argumentos");}
				break;

				
			case "--topWords":
				if(args.length>3){
					String file =args[1];
					String keys1[]= new String[0];
					int ext=0;
					int length=0;
					try{
						ext=Integer.parseInt(args[2]);
						
						length=Integer.parseInt(args[3]);
						SparkProcessor.SparkProcessWord(keys1,file,length);
						new Grapher(ext);
					}catch(NumberFormatException n){
						System.out.println("Introduce una extensión numérica");
					}
					
					
					
				}else{
					System.out.println("Comando no válido");
				}
				break;
				
			default:
				System.out.println("-> Error comando no válido");
			}

		} else {
			System.out.println("Seleccione una opción:\n");
			System.out.println("-> --dataset <Nombre del fichero de salida> <Campos de busqueda>\n");
			System.out.println("-> --sparkWord <Nombre del fichero de entrada> <Palabras seleccionadas>\n");
			System.out.println("-> --topWords <Nombre del fichero de entrada> <Límite de palabras> <Longitud mínima de palabra>\n");
		}
	}
}
