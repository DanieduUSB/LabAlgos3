class ListaAdyacenciaGrafo<T> : Grafo<T> {
    private val lista: MutableList<MutableList<T>> = mutableListOf()

    override fun agregarVertice(v: T): Boolean {
        if (contiene(v)) return false
        lista.add(mutableListOf(v))
        return true
    }

    override fun eliminarVertice(v: T): Boolean {
        if (!contiene(v)) return false

        var i = 0
        while (i < tamano()) {
            val arcos = lista[i]
            if (v == arcos[0]) {
                lista.remove(arcos)
                continue
            }
            lista[i] = arcos.filter { it != v }.toMutableList()
            i++
        }
        return true
    }

    override fun conectar(desde: T, hasta: T): Boolean {
        if (!contiene(hasta)) return false

        for (arcos in lista) {
            if (arcos[0] == desde) {
                if (hasta in arcos.subList(1, arcos.size)) return false
                arcos.add(hasta)
                return true
            }
        }
        return false
    }

    override fun contiene(v: T): Boolean {
        for (arcos in lista) {
            if (v == arcos[0]) return true
        }
        return false
    }

    override fun obtenerArcosSalida(v: T): List<T> {
        for (arcos in lista) {
            if (arcos[0] == v) {
                return arcos.subList(1, arcos.size)
            }
        }
        return emptyList()
    }

    override fun obtenerArcosEntrada(v: T): List<T> {
        val salida = mutableListOf<T>()
        for (arcos in lista) {
            if (v in arcos.subList(1, arcos.size)) salida.add(arcos[0])
        }
        return salida.toList()
    }

    override fun tamano(): Int = lista.size

    override fun subgrafo(vertices: Collection<T>): Grafo<T> {
        val nuevoGrafo = ListaAdyacenciaGrafo<T>()
        for (arcos in lista) {
            if (arcos[0] in vertices) {
                val nuevosArcos = arcos.filter { it in vertices }.toMutableList()
                nuevoGrafo.lista.add(nuevosArcos)
            }
        }
        return nuevoGrafo
    }

    override fun toString(): String {
        var extSalida = ""
        for (arcos in lista) {
            var intSalida = arcos[0].toString() + " ->"
            for (vertice in arcos.subList(1, arcos.size)) {
                intSalida = intSalida + " " + vertice.toString() + " ->"
            }
            intSalida = intSalida + " /\n🡓 \n"
            extSalida = extSalida + intSalida
        }
        extSalida = extSalida + "/"
        return extSalida + " \nTamaño: ${tamano()}"
    }

}