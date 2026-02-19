Laboratorio #1 (CI2693)

Daniel Quijada (20-10518)



Instrucciones para COMPILAR el programa (Desde terminal en Linux)

	1- Abrir la terminal de la distribución de Linux correspondiente.

	2- Ingresar en la carpeta en la que se encuentre el laboratorio con el comando cd.

	3- Escribir el comando 'make' en la terminal.

Instrucciones para CORRER el programa (Desde terminal en Linux)

	1- Compilar el programa.

	2- Colocar en la carpeta del laboratorio el archivo 'input.txt' (Ver Instrucciones input.txt para mas
	información al respecto).

	3- Usar el comando './run.sh (nombreA) (nombreB)'. Si no se especifican exactamente dos nombres, el
	programa terminará la ejecución con un error.

Instrucciones input.txt

	1- Cada línea contiene exactamente dos nombres separados por un espacio.

	2- Los nombres son sensibles a mayúsculas y minúsculas, es decir, 'Ana' es distinto de 'ana'.

	3- El archivo puede estar vacío, sin embargo, no es recomendable ya que no importa que par de nombres se
	especifiquen al correr el programa, siempre retornará -1

	4- El nombre del archivo no puede variar.

	Si alguno de estos parámetros no se cumplen, el programa podría no funcionar correctamente, e incluso,
	emitir error al ejecutar.

Programa

	El programa cuenta con tres librerías: Grafo.kt, ListaAdyacenciaGrafo.kt y DegreesOfSeparation.kt.
	
	- Las librerías Grafo.kt y ListaAdyacenciaGrafo.kt fueron tomadas directamente el proyecto #1, por lo tanto
	la explicación de estas se encontrarán en su respectivo README.md
	

	- La librería DegreesOfSeparation.kt se encarga de imprimir el grado de separación entre las dos personas
	especificadas en el input. Contiene las funciones main(), loadGraph() y degreesOfSeparation().

		- main(args: Array<String>) es la función inicial. Recibe como entrada los argumentos del input (los
		nombres), y hace las llamadas a las funciones loadGraph() y degreesOfSeparation(). Además, imprime por
		la salida estándar los grados de separación entre las personas antes de finalizar la ejecución.

		- loadGraph(): Grafo<String> hace uso de las librerías java.io.File y java.io.BufferedReader para leer
		e interpretar como grafo la información de input.txt.

		- degreesOfSeparation(graph: Grafo<String>, nameA: String, nameB: String): Int Se encarga de retornar el
		grado de separación entre 'nameA' y 'nameB' en el grafo 'graph', haciendo uso del algoritmo BFS con
		pequeñas modificaciones para retornar un entero en vez de una matriz.

		Dado que no es tan fácil obtener la lista de vértices en la implementación de grafo usada, se utiliza la
		variable 'visited', un mapa mutable que mapea Strings a Booleanos, de manera que si visited[nombre] es
		iual a true, 'nombre' ya fue visitado, mientras que si es igual a 'null', no lo fue.

		Se utilizan dos variables 'toCheckCurrentLevel' y 'toCheckNextLevel' para llevar el conteo en cada
		iteración de cuántos vértices faltan por revisar en el nivel actual y cuántos hay que revisar en el
		siguiente nivel. Definamos por nivel la distancia mas corta entre el vértice inicial 'nameA' y el vértice
		que se revisa en algunas de las iteraciones 'vertex'.

		La variable 'degreesOfSeparation' es la variable a retornar. Aumenta en uno cada vez que se pasa a un
		nuevo nivel en el grafo, lo cual ocurre cada vez que 'toCheckCurrentLevel' es cero. Esta variable
		representa la distancia entre el vértices 'nameA' y el vértice 'vertex'

		Cuando 'sucesor' es igual a 'nameB' significa que se encontró una cadena entre 'nameA' y 'nameB', en
		particular, como usamos BFS, la cadena que se encontró es la de longitud mas corta, y la distancia de esa
		cadena es 'degreesOfSeparation'.

		Si el ciclo termina, significa que nunca hubo un 'sucesor' igual a 'nameB', es decir, no hay una cadena
		entre 'nameA' y 'nameB', por lo tanto se retorna -1.
