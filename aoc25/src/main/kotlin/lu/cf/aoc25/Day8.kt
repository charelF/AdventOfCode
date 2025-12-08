package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println
import kotlin.math.pow
import kotlin.math.sqrt

object Day8 {
    val reader = Reader(8)

    class Point(val x: Float, val y: Float, val z: Float) {
        private fun distance(other: Point): Float {
            return sqrt(
                (this.x - other.x).pow(2) +
                    (this.y - other.y).pow(2) +
                    (this.z - other.z).pow(2),
            )
        }

        operator fun minus(other: Point) {
        }
    }

    fun main() {
        // val input = reader.actual().readLines()
        val input = reader.samples().first().readLines()
        solve(input, 1)
        // solve(input, 2)
    }

    private fun solve(
        input: List<String>,
        part: Int,
    ) {
        input.println()
    }
}
