package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader


object Day1 {
    val reader = Reader(1)

    fun main() {
        // val lines = reader.samples().first()
        val lines = reader.actual().readLines()

        var total = 50
        var zeroCount = 0
        var zeroCount2 = 0

        lines.forEach { line ->
            val rot = line.first()
            val num = line.drop(1).toInt()
            // some ugly error is when start at 0, then we dont cross 0 again to go negative bs
            if (total == 0 && rot=='L') zeroCount2--
            when (rot) {
                'L' -> total -= num
                'R' -> total += num
            }
            while (total < 0) {
                total += 100
                zeroCount2++
            }
            while (total >= 100) {
                total -= 100
                zeroCount2++
            }
            // same ugly error as above
            if (total == 0 && rot=='L') zeroCount2++
            if (total == 0) zeroCount++
        }
        println("part 1: $zeroCount")
        println("part 2: $zeroCount2")
    }
}