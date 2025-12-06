package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println

object Day6 {
    val reader = Reader(6)

    fun main() {
        val input = reader.actual().readLines()
//        val input = reader.samples().first().readLines()
        p1(input)
        p2(input)
    }

    private fun p2(input: List<String>) {
        val grid = input.map { line ->
            line.map { it }
        }
        val operators = input.last().trim().split(" +".toPattern()).reversed()

        val rot = Array(size=grid.first().size) { CharArray(size=grid.size-1) }
        for (i in (0..<input.first().length).reversed()) {
            for (j in (0..<input.size-1)) {
                rot[input.first().length - 1 - i][j] = grid[j][i]
            }
        }
        val accList = mutableListOf<MutableList<Long>>()
        var acc = mutableListOf<Long>()
        rot.toList().map { charArray ->
            val string =  charArray.concatToString().trim()
            if (string == "") {
                accList.add(acc)
                acc = mutableListOf<Long>()
            }
            else {
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
        val grid = input.map { line ->
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
        println("part 1: $total")
    }
}
