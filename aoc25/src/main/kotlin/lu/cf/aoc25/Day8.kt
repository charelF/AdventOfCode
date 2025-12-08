package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println
import kotlin.math.pow
import kotlin.math.sqrt

object Day8 {
    val reader = Reader(8)

    class Point(val x: Double, val y: Double, val z: Double) {
        companion object {
            fun parse(line: String): Point {
                val (x, y, z) = line.split(",").map { it.trim().toDouble() }
                return Point(x, y, z)
            }
        }

        private fun distance(other: Point): Double {
            return sqrt(
                (this.x - other.x).pow(2) +
                    (this.y - other.y).pow(2) +
                    (this.z - other.z).pow(2),
            )
        }

        operator fun minus(other: Point) = distance(other)

        override fun toString() = "($x $y $z)"

        override fun equals(other: Any?): Boolean {
            if (other !is Point) return false
            return this.x == other.x && this.y == other.y && this.z == other.z
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
        val points = input.map(Point::parse)
        println(points.size)
        
        val distances = Array(points.size) { i ->
            DoubleArray(points.size) { j ->
                points[i] - points[j]
            }
        }
        
        distances.println("%8.3f")
        
        
//        val minP: Point
//        val minQ: Point
//        val minD: Float
//        for (p in points.keys) {
//            for (q in points.keys) {
//                if (p == q) continue
//                val d = p - q
//                if 
//            }
//        }
//        
        
    }
}
