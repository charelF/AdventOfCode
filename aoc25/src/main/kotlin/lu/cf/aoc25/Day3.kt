package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println

object Day3 {
    val reader = Reader(3)

    fun main() {
        // val input = reader.actual().readLines()
        val input = reader.samples().first().readLines()
        solve(input, 1)
        solve(input, 2)
    }

    private fun solve(
        input: List<String>,
        part: Int,
    ) {
        val digits = if (part == 1) 2 else 12

        input.sumOf { line ->
            val digitMap = (0..<digits).associateWith { (0 to 0) }.toMutableMap()

            repeat(digits) { digit ->
                line.map { "$it".toInt() }.forEachIndexed { idx, battery ->
                    if (idx > (digitMap[digit - 1]?.second ?: -1)) {
                        if (idx < line.length - (digits - digit - 1)) {
                            if (battery > digitMap[digit]!!.first) {
                                digitMap[digit] = (battery to idx)
                            }
                        }
                    }
                }
            }
            digitMap.map { (_, v) -> v.first }.joinToString("").toLong()
        }.also { println("part $part: $it") }
    }
}
