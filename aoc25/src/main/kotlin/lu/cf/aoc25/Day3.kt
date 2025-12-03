package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println

object Day3 {
    val reader = Reader(3)

    fun main() {
        val input = reader.actual().readLines()
//        val input = reader.samples().first().readLines()

        input.map { line ->
            var max = 0
            var maxIdx = 0
            var max2 = 0
            var maxIdx2 = 0
            line.map { "$it".toInt() }
                .forEachIndexed { idx, battery ->
                    if (idx != line.length - 1) {
                        if (battery > max) {
                            max = battery
                            maxIdx = idx
                        }
                    }
                }

            line.map { "$it".toInt() }
                .drop(maxIdx+1)
                .forEachIndexed { idx, battery ->
                    if (battery > max2) {
                        max2 = battery
                        maxIdx2 = idx
                    }
                }
            (max.toString() + max2.toString()).toInt()
        }
            .sum().println()
    }
}