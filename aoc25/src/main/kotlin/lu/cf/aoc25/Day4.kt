package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.plus
import lu.cf.aoc25.domain.toInt

object Day4 {
    val reader = Reader(4)

    fun main() {
//        val grid = reader.samples().first().readLines()
        val grid =
            reader.actual().readLines()
                .flatMapIndexed { i, line ->
                    line.mapIndexed { j, char ->
                        (i to j) to char
                    }
                }
                .associate { (k, v) -> k to v }
                .toMutableMap()

        var prevtotal = -1
        var total = 0
        while (prevtotal != total) {
            prevtotal = total
            total +=
                grid.mapValues { (k, v) ->
                    val (i, j) = k
                    if (v == '@') {
                        val nb =
                            (grid[i - 1 to j - 1] == '@') +
                                (grid[i - 1 to j] == '@') +
                                (grid[i - 1 to j + 1] == '@') +
                                (grid[i to j - 1] == '@') +
                                (grid[i to j + 1] == '@') +
                                (grid[i + 1 to j - 1] == '@') +
                                (grid[i + 1 to j] == '@') +
                                (grid[i + 1 to j + 1] == '@')
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
        }
        println(total)
    }
}
