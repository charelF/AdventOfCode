package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt
import kotlin.math.max
import kotlin.math.min

object Day9 {
    val reader = Reader(9)
    
//    data class Point(val x: Int, val y: Int)
    
    data class Rectangle (val p1: Pair<Int, Int>, val p2: Pair<Int, Int>): Comparable<Rectangle> {
        val area: Long
        
        init {
            val tl = Pair(min(p1.first, p2.first), max(p1.second, p2.second))
            val tr = Pair(max(p1.first, p2.first), max(p1.second, p2.second))
            val br = Pair(max(p1.first, p2.first), min(p1.second, p2.second))
            val bl = Pair(min(p1.first, p2.first), min(p1.second, p2.second))
            val w = tr.first - bl.first + 1L
            val h = tl.second - bl.second + 1L
//            println(tl)
//            println(tr)
//            println(br)
//            println(bl)
//            println(w)
//            println(h)
            area = w * h
            
            
        }

        override fun compareTo(other: Rectangle): Int {
            return this.area.compareTo(other.area)
        }

        override fun toString(): String {
            return "(${p1.first}, ${p1.second}) - (${p2.first}, ${p2.second}) - A=$area"
        }


    }
    
    fun main() {
         val points = reader.actual().readLines().map { line ->
            val (p1, p2) = line.split(",").map { it.trim().toInt() }
            p1 to p2
        }

        val rectangles = points.flatMap { p ->
            points.map { q ->
                Rectangle(p,  q)
            }
        }
        rectangles.sorted().reversed().first().printIt()
        rectangles.sorted().reversed().first().area.printIt()

    }
}
