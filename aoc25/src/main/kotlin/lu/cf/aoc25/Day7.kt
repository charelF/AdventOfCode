package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt

object Day7 {
    val reader = Reader(7)

    fun main() {
//         val input = reader.actual().readLines()
        val input = reader.samples().first().readLines()
        p1(input)
    }

    private fun p1(input: List<String>) {
        val lines = input.map { it.toMutableList() }
        var splits = 0
        for (i in 1..<lines.size) {
            val a = i - 1
            for (j in 0..<lines.first().size) {
                when {
                    lines[a][j] == 'S' -> lines[i][j] = '|'
                    lines[i][j] == '^' && lines[a][j] == '|' -> {
                        lines[i][j - 1] = '|'
                        lines[i][j + 1] = '|'
                    }
                    lines[a][j] == '|' -> lines[i][j] = '|'
                }
                if (lines[a][j] == '|' && lines[i][j] == '^') splits++
            }
        }

        lines.map { it.joinToString(separator = "").printIt() }

        for (i in 1..<lines.size) {
            val a = i - 1
            for (j in 0..<lines.first().size) {
                when {
                    // the time is right i will do part 2
                }
            }
        }
    }
}
