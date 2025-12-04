package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.toInt

object Day4 {
    val reader = Reader(4)

    fun main() {
//        val grid = reader.samples().first().readLines()
        val grid = reader.actual().readLines()
            .flatMapIndexed { i, line ->
                line.mapIndexed { j, char ->
                    (i to j) to char
                }
            }
            .associate { (k, v) -> k to v }
            .toMutableMap()

        var total = 0
        repeat(10000) {
            val prevtotal = total
            total += grid.mapValues { (k, v) ->
                val (i, j) = k
                if (v == '@') {
                    val nb = (grid[i - 1 to j - 1] == '@').toInt() +
                            (grid[i - 1 to j] == '@').toInt() +
                            (grid[i - 1 to j + 1] == '@').toInt() +
                            (grid[i to j - 1] == '@').toInt() +
                            (grid[i to j + 1] == '@').toInt() +
                            (grid[i + 1 to j - 1] == '@').toInt() +
                            (grid[i + 1 to j] == '@').toInt() +
                            (grid[i + 1 to j + 1] == '@').toInt()
                    (nb < 4).toInt()
                } else {
                    0
                }
            }.map { (k, v) ->
                if (v == 1) {
                    grid[k.first to k.second] = 'x'
                }
                v
            }
                .sum()
            println(total)
            var prevk = -1
//            grid.forEach { (k, v) ->
////                println(k.first, pre)
//                if (k.first != prevk ) println("")
//                print(v)
//                prevk = k.first
//            }
            if (prevtotal == total) return@repeat
        }
        println(total)



//        val grid = reader.samples().first().readLines().mapIndexed { i, line ->
//            line.mapIndexed { j, char ->
//                var rolls = 0
//                if
//
//
//
//            }
//        }
    }

}
