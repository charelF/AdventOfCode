package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.plus
import lu.cf.aoc25.domain.toInt

object Day4 {
    val reader = Reader(4)

    fun main() {
//        val lines = reader.samples().first().readLines()
        val lines = reader.actual().readLines()
        solve(lines)
    }

    fun solve(lines: List<String>) {
        val grid =
            lines.flatMapIndexed { i, line ->
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
//                        val nb = (i-1 .. i+1).map { ii ->
//                            (j - 1..j + 1).map { jj ->
//                                (grid[ii to jj] == '@')
//                            }.sum()
//                        }.sum()

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
                    v.also {
                        if (v == 1) {
                            grid[k.first to k.second] = 'x'
                        }
                    }
                }.sum()
        }
        println(total)
    }
}
