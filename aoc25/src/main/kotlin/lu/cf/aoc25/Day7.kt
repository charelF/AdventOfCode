package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.Reader.Companion.toCharArray
import lu.cf.aoc25.domain.printIt

object Day7 {
    val reader = Reader(7)

    fun main() {
        // val input = reader.actual().readLines()
        val input = reader.samples().first().toCharArray()
        solve(input, 1)
        // solve(input, 2)
    }

    private fun solve(
        input: Array<CharArray>,
        part: Int,
    ) {
        input.printIt()
    }
}
