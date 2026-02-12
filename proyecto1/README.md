Proyecto #1 Laboratorio de Algoritmos 3 (CI2613)
Integrantes:
    Daniel Quijada 20-10518
    Brian Orta 21-10447

El programa cuenta con dos librerías: Grafo.kt y ListaAdyacenciaGrafo.kt

- Grafo.kt:
    Contiene la interfaz Grafo para los elementos que simulan el TAD Grafo

- ListaAdyacenciaGrafo.kt:
    Contiene la clase ListaAdyacenciaGrafo<T> para la implementación del TAD grafo usando como modelo de representación una lista de adyacencia mostrada en la clase teórica.

    La estructura principal de esta clase es la variable 'lista' de tipo MutableList<MutableList<T>>, con el fin de simular correctamente la representación de lista de adyacencia. Cada elemento de 'lista' es una lista por sí misma en la cual el primer elemento será el extremo inicial de cada arco y los siguientes elementos serán extremos finales de estos. Los métodos son los siguientes:

    (En lo siguiente, cuando se hable de la variable 'arcos', nos referimos a una de las lista mutables dentro de la variable 'lista', y nos referiremos a todas las listas mutables dentro de listas como los arcos)

    - agregarVertice(v: T): Boolean

        Si el grafo ya contiene el vértice v, retorna false, en caso contrario añade a 'lista' una lista mutable cuyo único elemento es el vértice v.

        Complejidad: O(n) (La verificación para ver si el grafo ya contiene al vértice es O(n))

    - eliminarVertice(v: T): Boolean

        Si el grafo no contiene el vértice v, retorna false, en caso contrario, se inicializa un ciclo while que en esencia itera sobre los arcos y termina cuando se haya iterado sobre estos. Dentro de cada iteración pueden ocurrir dos cosas:
            El primer vértice de 'arcos' es igual al vértice 'v' a eliminar, de manera que se elimina 'arcos' completamente de 'lista', quitando correctamente el vértice 'v' y todas sus conexiones, y luego se pasa a la siguiente iteración del ciclo.
            El primer vértices de 'arcos' es distinto al vértice a eliminar, por lo que debemos eliminar el vértice 'v' de 'arcos', para lo cual se usa un .filter
        Al finalizar el ciclo retorna true

        Complejidad: O(n+m) (Debe pasar por todos los vértices, y en cada uno de ellos, por todos los lados para eliminar el vértice 'v')

    - conectar(desde: T, hasta: T): Boolean

        Si el grafo no contiene el vértice 'hasta' retorna false, en caso contrario, itera sobre los arcos. Si el primer vértice de 'arcos' es igual a 'desde', verifica que la conexión no exista ya, es decir, verifica que 'hasta' no esté en las demás posiciones de la lista, y si no lo está, lo añade, y luego retorna true. Si el ciclo termina, significa que no consiguió una lista cuyo primer elemento sea 'desde', es decir, 'desde' no está en el grafo, por lo tanto retorna false.

        Complejidad: O(n) (Debe iterar sobre los vértices, y el único momento en el que itera sobre los lados es cuando llega al vértice 'desde', si es que llega)

    - contiene(v: T): Boolean

        Itera sobre los arcos, y en cada iteración comprueba si el vértice 'v' está en la primera posición de 'arcos'. Si en alguna iteración sí ocurre eso, retorna true, si no, termina el ciclo y retorna false. Esto es así debido a que las primeras posiciones de los arcos contienen todos los vértices del grafo, por lo cual si no esta en la primera posición de alguno de los arcos, no se encuentra en ningún lado.

        Complejidad: O(n) (Debe iterar únicamente sobre los vértices)

    - obtenerArcosSalida(v: T): List<T>

        La ejecución es altamente similar a la función contiene. Si consigue entre los arcos uno cuyo primer vértice sea 'v', retorna en forma de lista el resto de vértices. En caso contrario, retorna una lista vacía.

        Complejidad: O(n) (Debe iterar únicamente sobre los vértices)

    - obtenerArcosEntrada(v: T): List<T>

        Crea la lista de salida, donde va a ir añadiendo cada vértice. Itera sobre los arcos, y para cada uno, si 'v' está en 'arcos' (no verifica la posición 0), entonces añade el primer elemento de 'arcos' a la lista de salida, y por último retorna esta lista.

        Complejidad: O(n+m) (Itera sobre todos los vértices, y en cada uno de estos, itera sobre todos los lados)

    - tamano(): Int

        Retorna lista.size. Debido a que cada uno de los arcos debe tener en la primera posición un vértice que será siempre extremo inicial, todas las posiciones iniciales de las listas crean el conjunto de vértice del grafo, por lo que el tamaño de lista es el número de vértices.

        Complejidad: O(1) (Retorna el tamaño de 'lista')

    - subgrafo(vertices: Collection<T>): Grafo<T>

        Crea un nuevo grafo llamada 'nuevoGrafo', luego itera sobre los arcos. En cada iteración, verifica si el primer elemento de 'arcos' esta en 'vertices', y si es así, toma 'arcos' y lo filtra de manera que elimina los vértices que no están en 'vertices' y guarda esta nueva lista en 'nuevosArcos', luego añade la lista 'nuevosArcos' a la variable 'lista' de 'nuevoGrafo'. Básicamente toma los arcos existentes cuyo primer vértice sea uno que esté en 'vertices', filtra los demás para que queden solo los que estén en 'vertices' y agrega esta lista en el nuevo grafo, haciendo así que este solo contenga los vértices en 'vertices' y las respectivas conexiones entre estos.

        Complejidad: O(n+m) (Esto asume que el tamaño de 'vertices' << n. Debe iterar sobre los vértices, en cada uno verifica que el vértice inicial de la lista no este en 'vertices' y luego filtra los lados)

    - toString(): String

        Método por defecto de kotlin, usado para representar el grafo como string. Hecho con fines de debugging.

        Complejidad: O(n+m) (Itera sobre los vértices, y luego sobre los lados)