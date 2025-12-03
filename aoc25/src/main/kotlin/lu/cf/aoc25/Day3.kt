package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println

object Day3 {
    val reader = Reader(3)

    fun main() {
        p2()
//        p1()
    }

    fun p2() {
        val input = reader.actual().readLines()
//        val input = reader.samples().first().readLines()

        val digits = 12

        input.sumOf { line ->
            val max = (0..<digits).associate { it to (0 to 0) }.toMutableMap()//.println()

            println(line)
            repeat(digits) { digit ->
                val spareRoom = digits - digit - 1
                val prevIdx = digit - 1
                println("spareRoom: $spareRoom")
                line.map { "$it".toInt() }
                    .forEachIndexed { idx, battery ->
                        if (idx > (max[prevIdx]?.second ?: -1)) {
                            if (idx < line.length - spareRoom) {
                                if (battery > max[digit]!!.first) {
                                    max[digit] = (battery to idx)
                                }
                            }
                        }
                    }
            }
            println(max)
            max.map { (k, v) -> v.first }.joinToString("").toLong().println()
        }.println()
    }

    fun p1() {
        val input = reader.actual().readLines()

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

//            println("max0second: ${max[0]!!.second}")

            line.map { "$it".toInt() }
                .drop(maxIdx+1)
                .forEachIndexed { idx, battery ->
                    if (battery > max2) {
                        max2 = battery
                        maxIdx2 = idx
                    }
                }
            (max.toString() + max2.toString()).toInt()
        }.println()
            .sum().println()
    }

    fun px() {
        val input = reader.actual().readLines()

        input.map { line ->
            var max = 0
            var maxIdx = 0
            var max2 = 0
            var maxIdx2 = 0
            var max3 = 0
            var maxIdx3 = 0
            var max4 = 0
            var maxIdx4 = 0
            var max5 = 0
            var maxIdx5 = 0
            var max6 = 0
            var maxIdx6 = 0
            var max7 = 0
            var maxIdx7 = 0
            var max8 = 0
            var maxIdx8 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
//            var max2 = 0
//            var maxIdx2 = 0
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