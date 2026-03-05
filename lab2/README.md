Laboratorio #2 (CI2693)

Daniel Quijada (20-10518)



Instrucciones para COMPILAR el programa (Desde terminal en Linux)

	1- Abrir la terminal de la distribución de Linux correspondiente.

	2- Ingresar en la carpeta en la que se encuentre el laboratorio con el comando cd.

	3- Escribir el comando 'make' en la terminal.

Instrucciones para CORRER el programa (Desde terminal en Linux)

	1- Compilar el programa.

	2- Usar el comando './run.sh (Cadena de enteros)'. Ver instrucciones cadena de texto para formato

Instrucciones cadena de enteros

    La cadena de enteros representa un tablero de sudoku, en donde cada 9 números es una fila del sudoku y
    las filas se van colocando en orden desde arriba hacia abajo. La cadena debe tener longitud 81. Cada
    número debe estar entre 0 y 9, de manera que si un número es 0, indica que la casilla correspondiente es
    vacía, en caso contrario, la casilla contiene el número indicado.

    La cadena inicial debe obedecer las normas de un sudoku, es decir, no se puede repetir un número en la
    misma fila, en la misma columna ni en el mismo bloque.
    
    Ejemplo: se tiene la cadena 000040053000050080900000000000902000430000000000600000002000600000080900050100000
    que corresponde al siguiente sudoku:

    / / /  / 4 /  / 5 3
    / / /  / 5 /  / 8 /
    9 / /  / / /  / / /

    / / /  9 / 2  / / /
    4 3 /  / / /  / / /
    / / /  6 / /  / / /

    / / 2  / / /  6 / /
    / / /  / 8 /  9 / /
    / 5 /  1 / /  / / /

    Esta cadena es válida inicialmente, y el programa se encargará de buscar (Si existe) una solución

Programa

	El programa cuenta con la librería Sudoku.kt y contiene las siguientes funciones:
    
	-main(args: Array<String>) Es la función principal del programa. Verifica que la entrada sea válida y llama a
    la función que resuelve el sudoku, luego imprime la solución, en caso de que exista, o imprime 'NOSOLUTION'

    -validEntry(chain: String): Boolean Verifica que la cadena inicial sea un sudoku válido, con ayuda de las
    siguientes tres funciones auxiliares de validación:

        -validRow(chain: String, row: Int): Boolean

        -validCol(chain: String, col: Int): Boolean

        -validBlock(chain: String, block: Int): Boolean

        Las tres funciones anteriores básicamente verifican que no hayan números repetidos en la fila, columna y
        bloque especificado respectivamente. Se usa la misma lógica que el algoritmo counting sort para poder
        verificar repeticiones y la distinción entre cada función es la posición de la cadena a la que acceden, mas
        que la lógica en sí.

    -solve(sudoku: CharArray, pos: Int): Boolean Es la función principal encargada de resolver el sudoku.

    Se trabaja la variable 'sudoku' como un CharArray en lugar de un String debido a que los elementos de CharArray
    son mutables, lo que nos permite modificar la cadena original sin necesidad de crear mas variables.

    La variable 'pos' indica la posición de la cadena.

    La función usa la técnica de Backtracking aprendida en clase. Usa como caso base pos==81, lo que es equivalente a
    decir que se llegó al final de la cadena. El grafo de decisión sería tal que cada vértice representa un estado del
    sudoku y cada lado un número que puedo colocar en la posición correspondiente.

    Se usa como función auxiliar 'isValid' para ver si se puede colocar en la posición indicada el número seleccionado.

    -isValid(sudoku: CharArray, pos: Int, number: Char): Boolean Verifica si puede colocar 'number' en 'sudoku[pos]',
    para lo cual se para en la primera posición de la fila, columna y bloque de 'pos', y verifica que ningún otro número
    en estos sea igual a 'number'.

Complejidad del programa
    
    La entrada inicial es una cadena de texto de 81 caracteres, de manera que podemos definir nuestro n=81 un tamaño
    fijo de entrada. La verificación de la entrada es O(n), mientras que la función que resuelve el sudoku es O(9^(n-k)),
    donde k representa el número de recuadros del sudoku que ya contienen un número entre el 1 y el 9. Por lo tanto, el
    programa es O(9^(n-k)).
