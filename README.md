## Título:
**Herramienta de tweets**

## Autores:
* Daniel Cerezo Torrejón 
* Isabel Higueras de Jorge

## Objetivos:
Comenzamos el proyecto con la idea de poder emplear alguna librería para recopilar 
tweets en streaming de acuerdo a una determinada temática y posteriormente tratar 
esos datos a fin de extraer las frecuencias de determinadas palabras clave.

Finalmenmte decidimos desarrollar una herramienta que permitiera al usuario elegir 
sobre que palabras quiere realizar un dataset de tweets (obteniendolos en streaming)
y como quiere tratarlos, acorde a las funciones que se describirán más adelante.

## Tecnologías y librerías:
* [**Twitter4j:**](http://twitter4j.org/en/index.html) 

    Esta librería nos permite obtener las funciones necesarias para recopilar tweets 
    e interactuar con la api de Twitter.

* [**JFreeChart:**](http://www.jfree.org/jfreechart/)

    Esta librería esta pensada para la representación de gráficos.
    Genera un Jpanel con el gráfico que se haya implementado, sin embargo los datos 
dct que se le pasan a las funciones de esta librería han de estar previament, es decir,
    la librería solo se encarga de la representación, las frecuencias o los valores absolutos 
    se han de calcular previamente.

* [**Apache Spark:**](http://spark.apache.org/)     

    Empleada para el tratamiento de los datos, principalmente en este caso por la capacidad
    de map y reduce.
    
El proyecto se ha desarrollado en eclipse, además para la incorporación de las distintas
librerías se ha empleado Maven.

## Guía de uso:

### Pasos previos:
#### Eclipse:
En el archivo que se puede descargar en este proyecto se encuentra un zip que contiene el 
proyecto de eclipse, por lo tanto, para ver el contenido conviene importarlo a eclipse y modificar 
el archivo **twitter4j.properties** con las claves de la aplicación propias. Una vez hecho esto lo mejor es generar un 
jar ejecutable, seleccionando la opción de crear una carpeta auxiliar para las librerías.
Una vez exportado a jar colocar el archivo **twitter4j.properties** en el mismo directorio que el .jar


## Ejecución:
Como hemos mencionado conviene ejecutarlo en una terminal ya que se maneja mediante argumentos.
Si inicias el programa con java -jar (nombre_archivo.jar), el prograa mostrará un 
menú que indica las distintas funciones disponibles y la sintaxis.

### --dataset (Nombre del fichero de salida) (Campos de busqueda)
Esta función permite hacer un dataset de tweets (en streaming) que contengan alguna plabra o frase
especificada en los campos de búsqueda.
#### Recomendaciones:
* Escribir entrecomillados cada criterio de búsqueda, es obligatorio en el caso de querer
una frase (con espacios) como criterio.

### --sparkWord (Nombre del fichero de entrada) (Palabras seleccionadas)
Esta función se realiza con Spark sobre el archivo que se selecione como entrada (puede ser uno generado por la función anterior o cualquier txt)
Si no se introducen palabras seleccionadas el resultado que arroja está contenido en la carpeta salida 
y se llama part-00000 el cual contiene pares clave, valor donde el valor es el número de veces que se 
menciona esa palabra.

Si se establecen palabras seleccionadas en el archivo mencionado anteriormente se arroja como 
(useless,valor) la cuenta de todas las palabras fuera de las seleccionadas.
Además, en este caso, se muestra un gráfico de tipo pie que muestra la frecuencia de cada 
palabra con respecto al conjunto de las seleccionadas y se muestra en la consola el número de veces que 
se menciona cada palabra seleccionada.



### --topWords (Nombre del fichero de entrada) (Límite de palabras) (Longitud mínima de palabra)
Es una variación de la función anterior, pero en este caso se muestra un gráfico con las 
N palabras más mencionadas (N = Límite de palabras) que tengan una longitud superior o
igual a la longitud especificada. 

De nuevo las frecuencias obtenidas son relativas al conjunto de las N palabras más mencionadas.
En adición, la consola muestra el número de veces que se mencionan.

## Resultados
Esta sección está determinada en el apartado anterior.
Hemos conseguido extraer varios datasets, que nos han permitido con la aplicación desarrollada
saber que marcas han sido las mñás comentadas durante el CES 2017, o las más mencionadas en los 
últimos días en el sector automovilístico relacionadas con la movilidad eléctrica, e incluso 
a que operadoras se han dirigido con más frecuencia los twitteros.

El problema, es que para obtener datos consistentes conviene recoger tweets durante varios días
y largos periodos, o tomar el análisis en su contexto temporal, ya que los resultados van a variar en
función de las noticias, etc..

### Ejemplos
* Con el dataset sobre el coche eléctrico:
![alt text](https://github.com/danielct1995/Herramienta-Twitter/blob/master/Ejemplos/coche_electrico.png?raw=true "Sobre coche eléctrico")

* Con un dataset de mensajes directos de twitteros a las operadoras:
![alt text](https://github.com/danielct1995/Herramienta-Twitter/blob/master/Ejemplos/operadoras.png?raw=true "Operadoras más referidas")


## Conclusiones:
El desarrollo de la aplicación ha resualtado interesante, ya que ha supuesto entender
o al menos comenzar a entender, el funcionamiento de spark, así como la unión de distintas bibliotecas.

Sin embargo, nos hubira gustado haber tenido la capacidad y el tiempo de profundizar en el tratamiento con spark.









