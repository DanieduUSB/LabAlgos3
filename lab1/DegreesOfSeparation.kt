import java.io.File
import java.io.BufferedReader

fun loadGraph(): Grafo<String> {
    val file = File("input.txt")
    val reader = file.bufferedReader()
    val graph = ListaAdyacenciaGrafo<String>()
    var line = reader.readLine()
    while (line != null) {
        val names: List<String> = line.split(" ")
        if (names.size != 2) error("Formato de archivo inválido")
        graph.agregarVertice(names[0])
        graph.agregarVertice(names[1])
        graph.conectar(names[0],names[1])
        graph.conectar(names[1],names[0])
        line = reader.readLine()
    }
    return graph
}

fun degreesOfSeparation(graph: Grafo<String>, nameA: String, nameB: String): Int {
    if (!graph.contiene(nameA) || !graph.contiene(nameB)) return -1
    if (nameA == nameB) return 0

    val visited: MutableMap<String, Boolean> = mutableMapOf()
    visited[nameA] = true

    val queue = ArrayDeque<String>()
    queue.addLast(nameA)

    var toCheckCurrentLevel = 1
    var toCheckNextLevel = 0
    var degreesOfSeparation = 1

    while(queue.isNotEmpty()) {
        val vertex = queue.removeFirst()

        for (sucesor in graph.obtenerArcosSalida(vertex)) {
            if (sucesor == nameB) return degreesOfSeparation

            if (visited[sucesor] == null) {
                visited[sucesor] = true
                toCheckNextLevel++
                queue.addLast(sucesor)
            }
        }

        toCheckCurrentLevel--
        if (toCheckCurrentLevel == 0) {
            degreesOfSeparation++
            toCheckCurrentLevel = toCheckNextLevel
            toCheckNextLevel = 0
        }
    }
    return -1
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Debe introducir dos nombres")
        return
    }

    val graph = loadGraph()
    val nameA = args[0]
    val nameB = args[1]

    println(degreesOfSeparation(graph, nameA, nameB))
}