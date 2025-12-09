package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt
import kotlin.math.max
import kotlin.math.min

object Day9 {
    val reader = Reader(9)
    
    data class Rectangle (val p1: Pair<Int, Int>, val p2: Pair<Int, Int>): Comparable<Rectangle> {
        val tl: Pair<Int, Int> = Pair(min(p1.first, p2.first), max(p1.second, p2.second))
        val tr: Pair<Int, Int> = Pair(max(p1.first, p2.first), max(p1.second, p2.second))
        val br: Pair<Int, Int> = Pair(max(p1.first, p2.first), min(p1.second, p2.second))
        val bl: Pair<Int, Int> = Pair(min(p1.first, p2.first), min(p1.second, p2.second))
        val area: Long = (tr.first - bl.first + 1L) * (tl.second - bl.second + 1L)

        override fun compareTo(other: Rectangle) = this.area.compareTo(other.area)

        override fun toString() = "(${p1.first}, ${p1.second}) - (${p2.first}, ${p2.second}) - A=$area"
    }
    
    fun buildBorder(points: List<Pair<Int, Int>>): MutableSet<Pair<Int, Int>> {
        val border: MutableSet<Pair<Int, Int>> = mutableSetOf()
        for (i in 0 .. points.size) {
            val p1 = points[i % points.size]
            val p2 = points[(i+1) % points.size]
            (min(p1.first, p2.first) .. max(p1.first, p2.first)).forEach { border.add(it to p1.second) }
            (min(p1.second, p2.second) .. max(p1.second, p2.second)).forEach { border.add(p1.first to it) }
        }
        return border
    }
        
    
    fun main() { 
        val input = reader.samples().first().readLines()
//         val input = reader.actual().readLines()
        
        val points = input.map { line -> line.split(",").map { it.trim().toInt() }.let { (x, y) -> x to y } }
        val rectangles = points.flatMap { p -> points.map { q -> Rectangle(p,  q) } }
        
        rectangles.sorted().reversed().first().area.printIt()

        println("p2")
        buildBorder(points).printIt()
    }
}
