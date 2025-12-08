package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import kotlin.math.pow
import kotlin.math.sqrt

object Day8 {
    val reader = Reader(8)

    class Point(val x: Int, val y: Int, val z: Int) : Comparable<Point> {
        companion object {
            fun parse(line: String): Point {
                val (x, y, z) = line.split(",").map { it.trim().toInt() }
                return Point(x, y, z)
            }
        }

        private fun distanceTo(other: Point): Float {
            return sqrt(
                (this.x - other.x).toFloat().pow(2) +
                    (this.y - other.y).toFloat().pow(2) +
                    (this.z - other.z).toFloat().pow(2),
            )
        }

        operator fun minus(other: Point) = distanceTo(other)

        override fun toString() = "$x|$y|$z"

        override fun equals(other: Any?): Boolean {
            if (other !is Point) return false
            return this.x == other.x && this.y == other.y && this.z == other.z
        }

        // necessary so we can order them so we do not have the double pairs (x,y) and (y,x))
        override fun compareTo(other: Point) = this.hashCode().compareTo(other.hashCode())
    }

    fun main() {
        // solve(reader.samples().first().readLines(), 10)
        solve(reader.actual().readLines(), 1000)
    }

    private fun solve(
        input: List<String>,
        nConnections: Int,
    ) {
        val points = input.map(Point::parse)

        val sortedPairs =
            points.flatMap { p ->
                points.mapNotNull { q ->
                    when (p.compareTo(q)) {
                        1 -> p to q
                        -1 -> q to p
                        else -> null
                    }
                }
            }.toSet().sortedBy { (p, q) -> p - q }

        val circuits: MutableMap<Int, MutableSet<Point>> = mutableMapOf()

        var lastCircuit = 0
        var i = 0

        for ((p, q) in sortedPairs) {
            val pGroup = circuits.filterValues { it.contains(p) }.keys.firstOrNull()
            val qGroup = circuits.filterValues { it.contains(q) }.keys.firstOrNull()
            when {
                pGroup == null && qGroup == null -> circuits[lastCircuit++] = mutableSetOf(p, q)
                pGroup == null -> circuits[qGroup]!!.add(p)
                qGroup == null -> circuits[pGroup]!!.add(q)
                pGroup == qGroup -> Unit
                else -> circuits[pGroup]!!.addAll(circuits.remove(qGroup)!!)
            }

            if (i++ > nConnections) {
                if (i == nConnections + 2) {
                    circuits.values.map { it.size }.sorted().reversed().take(3).reduce { acc, next -> acc * next }
                        .also { println("part 1: $it") }
                }
                if (circuits.values.size == 1 && circuits.values.first().size == points.size) {
                    println("part 2: ${p.x * q.x}")
                    break
                }
            }
        }
    }
}
