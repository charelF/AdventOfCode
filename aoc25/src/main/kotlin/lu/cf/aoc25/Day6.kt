package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println

object Day6 {
    val reader = Reader(6)

    fun main() {
//        val input = reader.actual().readLines()
        val input = reader.samples().first().readLines()
        println(input)
        p1(input)
        p2(input)
    }

    private fun p2(input: List<String>) {

        val rot: List<List<Char>>
        for (i in 0..<input[0].size) {
    }

    private fun p1(input: List<String>) {
        val grid = input.map { line ->
            line.trim().split(" +".toPattern()).println()
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
                        acc = if (mul) {
                            acc * x.toLong()
                        } else {
                            acc + x.toLong()
                        }
                    }
                }
            }
            total += acc
        }
        println(total)
    }
}
