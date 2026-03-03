fun validEntry(chain: String): Boolean {
	
	for (row in 0 until 9) {
		if (!validRow(chain,row)) return false
	}
	for (col in 0 until 9) {
		if (!validCol(chain,col)) return false
	}
	for (block in 0 until 9) {
		if (!validBlock(chain,block)) return false
	}

	return true
}

fun validRow(chain: String, row: Int): Boolean {
	val numbers = IntArray(10) {0}
	for (col in 0 until 9) {
		val number = chain[9*row+col].digitToInt()
		if (number != 0) {
			numbers[number]++
			if (numbers[number] > 1) return false
		}
	}
	return true
}

fun validCol(chain: String, col: Int): Boolean {
	val numbers = IntArray(10) {0}
	for (row in 0 until 9) {
		val number = chain[9*row+col].digitToInt()
		if (number != 0) {
			numbers[number]++
			if (numbers[number] > 1) return false
		}
	}
	return true
}

fun validBlock(chain: String, block: Int): Boolean {
	val numbers = IntArray(10) {0}
	var pos = 3*block + 18*(block / 3)
	var aux = 0
	repeat(9) {
		val number = chain[pos].digitToInt()
		if (number != 0) {
			numbers[number]++
			if (numbers[number] > 1) return false
		}
		pos++
		aux++
		if (aux == 3) {
			aux = 0
			pos = pos + 6
		}
		
	}
	return true
}

fun solve(sudoku: CharArray, pos: Int): Boolean {
	if (pos == 81) return true

	if (sudoku[pos] != '0') return solve(sudoku,pos+1)

	for (i in 1..9) {
		val number = i.digitToChar()

		if (isValid(sudoku,pos,number)) {
			sudoku[pos] = number

			if (solve(sudoku,pos+1)) return true

			sudoku[pos] = '0'
		}
	}
	return false
}

fun isValid(sudoku: CharArray, pos: Int, number: Char): Boolean {

	val row = pos / 9
	val col = pos % 9

	var rowCheckIndex = col
	var colCheckIndex = pos - col
	var blockCheckIndex = pos - col % 3 - 9 * (row % 3)
	var aux = 0

	repeat(9) {
		if (pos != rowCheckIndex && sudoku[rowCheckIndex] == number) return false
		rowCheckIndex = rowCheckIndex + 9

		if (pos != colCheckIndex && sudoku[colCheckIndex] == number) return false
		colCheckIndex++
		
		if (aux == 3) {
			aux = 0
			blockCheckIndex = blockCheckIndex + 6
		}
		if (pos != blockCheckIndex && sudoku[blockCheckIndex] == number) return false
		blockCheckIndex++
		aux++
	}
	
	return true
}

fun main(args: Array<String>) {

	if (args.size != 1) {
		println("Se debe colocar una sola cadena de texto de 81 caracteres")
		return
	}

	if (args[0].length != 81) {	
		println("La cadena de texto introducida debe ser de 81 caracteres")
		return
	}
	
	val chain = args[0]

	for (char in chain) {
		if (char.code < 48 || char.code > 57) {
			println("La cadena de texto debe contener únicamente números del 0 al 9")
			return
		}
	}
	
	if (!validEntry(chain)) {
		println("NOSOLUTION")
		return
	}

	val sudoku = chain.toCharArray()

	if (solve(sudoku,0)) {
		val exit = sudoku.joinToString("")
		println(exit)

		// Las siguiente línea es un print humanamente legible, para fines de testeo
		//humanPrint(sudoku)
	} else {
		println("NOSOLUTION")
	}
}

fun humanPrint(sudoku: CharArray) {
	for ((pos,char) in sudoku.withIndex()) {
		if (pos % 27 == 0) {
			println("\n")
		} else if (pos % 9 == 0) {
			println()
		} else if (pos % 3 == 0) {
			print("  ")
		}
		print("$char ")
	}
	println()
}
