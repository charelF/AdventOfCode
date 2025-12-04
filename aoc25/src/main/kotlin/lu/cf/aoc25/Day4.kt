package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println
import lu.cf.aoc25.domain.toInt

object Day4 {
    val reader = Reader(4)

    fun main() {
//        val map = reader.samples().first().readLines()
        val map = reader.actual().readLines()
            .flatMapIndexed { i, line ->
                line.mapIndexed { j, char ->
                    (i to j) to char
                }
            }
            .associate { (k, v) -> k to v }
            .toMutableMap()

        map.map { (k, v) ->
            val (i, j) = k
            if (v == '@') {
                val nb = (map[i-1 to j-1] == '@').toInt() +
                        (map[i-1 to j] == '@').toInt() +
                        (map[i-1 to j+1] == '@').toInt() +
                        (map[i to j-1] == '@').toInt() +
                        (map[i to j+1] == '@').toInt() +
                        (map[i+1 to j-1] == '@').toInt() +
                        (map[i+1 to j] == '@').toInt() +
                        (map[i+1 to j+1] == '@').toInt()
                println("$i $j $nb")
                (nb < 4).toInt()
            } else {
                0
            }
        }.sum().println()



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
