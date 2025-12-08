package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader

object Day6 {
    val reader = Reader(6)

    fun main() {
        val input = reader.actual().readLines()
//        val input = reader.samples().first().readLines()
        p1(input)
        p2(input)
    }

    private fun p2(input: List<String>) {
        val grid = input.map { line -> line.map { it } }
        val operators = input.last().trim().split(" +".toPattern()).reversed()

        val height = grid.size - 1 // subtract last row of operators
        val length = grid.first().size
        val rotated = Array(length) { CharArray(height) }
        for (i in 0..<length) {
            for (j in (0..<height)) {
                rotated[i][j] = grid[j][length - 1 - i]
            }
        }

//        println(rotated.toList().map { it.toList().println() })

        val accList = mutableListOf<MutableList<Long>>()
        var acc = mutableListOf<Long>()
        rotated.toList().map { charArray ->
            val string = charArray.concatToString().trim()
            if (string == "") {
                accList.add(acc)
                acc = mutableListOf()
            } else {
                acc.add(string.toLong())
            }
        }
        accList.add(acc)

        accList.mapIndexed { i, sublist ->
            when (operators[i]) {
                "+" -> sublist.fold(0L) { acc, v -> acc + v }
                "*" -> sublist.fold(1L) { acc, v -> acc * v }
                else -> 0
            }
        }.sum().also { println("part 2: $it") }
    }

    private fun p1(input: List<String>) {
        val grid =
            input.map { line ->
                line.trim().split(" +".toPattern())
            }
        var total = 0L
        var mul = true
        for (i in 0..<grid[0].size) {
            var acc = 0L
            for (j in (0..<grid.size).reversed()) {
                val x = grid[j][i]
                when (x) {
                    "*" -> {
                        mul = true
                        acc = 1L
                    }
                    "+" -> {
                        mul = false
                        acc = 0L
                    }
                    else -> {
                        acc =
                            if (mul) {
                                acc * x.toLong()
                            } else {
                                acc + x.toLong()
                            }
                    }
                }
            }
            total += acc
        }
        println("part 1: $total")
    }
}
