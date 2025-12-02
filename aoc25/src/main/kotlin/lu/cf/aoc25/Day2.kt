package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader

object Day2 {
    val reader = Reader(2)

    fun main() {
        val input = reader.actual().readText()
        part1(input)
        part2(input)
    }

    fun part1(input: String) {
        var total = 0L
        input.split(',').forEach { range ->
            val (start, end) = range.split('-').map { it.toLong() }
            (start .. end).forEach { intCode ->
                val code = intCode.toString()
                if (code.length % 2 == 0) {
                    val fh = code.take(code.length / 2)
                    val lh = code.takeLast(code.length / 2)
                    if (fh == lh) total += intCode
                }
            }
        }
        println("part 1: $total")
    }

    fun part2(input: String) {
        val codes = mutableSetOf<Long>()
        input.split(',').forEach { range ->
            val (start, end) = range.split('-').map { it.toLong() }
            (start .. end).forEach { intCode ->
                val code = intCode.toString()
                for (groupLength in (1 .. code.length/2).reversed()) {
                    val candidate = code.take(groupLength)
                    var allMatch = true
                    for (startIndex in groupLength ..< code.length step groupLength) {
                        val endIndex = startIndex + groupLength
                        if (endIndex > code.length) {
                            allMatch = false
                            break
                        }
                        if (code.substring(startIndex, endIndex) != candidate) {
                            allMatch = false
                            break
                        }
                    }
                    if (allMatch) codes.add(intCode)
                }
            }
        }
        println("part 2: ${codes.sum()}")
    }
}