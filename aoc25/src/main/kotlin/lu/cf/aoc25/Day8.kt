package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt
import kotlin.math.pow
import kotlin.math.sqrt

object Day8 {
    val reader = Reader(8)

    class Point(val x: Int, val y: Int, val z: Int): Comparable<Point> {
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
        
        override fun compareTo(other: Point) = this.hashCode().compareTo(other.hashCode())
    }
    
    fun main() {
        solve(reader.samples().first().readLines(), 10)
        solve(reader.actual().readLines(), 1000)
    }

    private fun solve(
        input: List<String>,
        nConnections: Int,
    ) {
        val points = input.map(Point::parse)
        println(points.size)
        
        val sortedPairs = points.flatMap { p ->
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
            if (i++ >= nConnections) break
            val pGroup = circuits.filterValues { it.contains(p) }.keys.also { require(it.size <= 1) }.firstOrNull()
            val qGroup = circuits.filterValues { it.contains(q) }.keys.also { require(it.size <= 1) }.firstOrNull()
            when {
                pGroup == null && qGroup == null -> circuits[lastCircuit++] = mutableSetOf(p, q)
                pGroup == null -> circuits[qGroup]!!.add(p)
                qGroup == null -> circuits[pGroup]!!.add(q)
                pGroup == qGroup -> continue
                else -> circuits[pGroup]!!.addAll(circuits.remove(qGroup)!!)
            }
            println(circuits)
            circuits.values.map { it.size }.printIt()
        }
        println(circuits)
        
        circuits.values.map { it.size }.sorted().reversed().take(3).reduce { acc, next -> acc * next }.printIt()
        
        

        
        
        
        
//        val distances = Array(points.size) { i ->
//            FloatArray(points.size) { j ->
//                points[i] - points[j]
//            }
//        }
//        
//        distances.printIt("%12.3f")
        
//        val distances = points.flatMapIndexed { i, p ->
//            points.associateWith { q -> p - q
//            }
//        }
        
        
        
        
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
