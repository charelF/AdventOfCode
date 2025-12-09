package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt
import kotlin.math.max
import kotlin.math.min

object Day9 {
    val reader = Reader(9)
    
    data class Point(val x: Int, val y: Int)
    
    data class Polygon(val border: MutableSet<Point>) {
        val minX = border.minBy { it.x }.x - 1
        val minY = border.minBy { it.y }.y - 1
        val maxX = border.maxBy { it.x }.x + 1
        val maxY = border.maxBy { it.y }.y + 1
        
        companion object {
            fun fromPoints(points: List<Point>): Polygon {
                val border: MutableSet<Point> = mutableSetOf()
                for (i in 0..points.size) {
                    val p1 = points[i % points.size]
                    val p2 = points[(i + 1) % points.size]
                    (min(p1.x, p2.x)..max(p1.x, p2.x)).forEach { border.add(Point(it, p1.y)) }
                    (min(p1.y, p2.y)..max(p1.y, p2.y)).forEach { border.add(Point(p1.x, it)) }
                }
                return Polygon(border)
            }
        }
        
        
    }
    
    data class Rectangle (val p1: Point, val p2: Point): Comparable<Rectangle> {
        val minX = min(p1.x, p2.x)
        val minY = min(p1.y, p2.y)
        val maxX = max(p1.x, p2.x)
        val maxY= max(p1.y, p2.y)
        val area: Long = (maxX - minX + 1L) * (maxY - minY + 1L)

        override fun compareTo(other: Rectangle) = this.area.compareTo(other.area)

        override fun toString() = "(${p1.x}, ${p1.y}) - (${p2.x}, ${p2.y}) - A=$area"
        
        private fun getAllPoints(): Set<Point> {
            return (minX .. maxX).flatMap { x -> (minY .. maxY).map { y -> Point(x, y) } }.toSet()
        }
        
        /** Ray Casting Algorithm **/
        fun isWithin(polygon: Polygon): Boolean {
            return getAllPoints().all { point -> 
//                println(point)
                if (point in polygon.border) true
                else {
                    var flips = 0
//                    println("polygonmaxX: ${polygon.maxX}")
                    for (x in point.x..polygon.maxX) {
                        val p = Point(x, point.y)
                        val p2 = Point(x + 1, point.y)
//                        println("  p: $p - in border: ${p in polygon.border}")
//                        println("  p2: $p2 - in border: ${p2 in polygon.border}")
                        if ((p in polygon.border) && (p2 !in polygon.border)) flips++
//                        println("  flips: $flips")
                    }
                    // If the number of intersections is odd, the point is inside the polygon.
                    val z = flips % 2 == 1
//                    println("flips: $flips")
//                    println(polygon.border)
//                    println(z)
//                    println("--------")
                    z
                }
            }
        }
    }
    
    fun main() { 
//        val input = reader.samples().first().readLines()
         val input = reader.actual().readLines()
        
        val points = input.map { line -> line.split(",").map { it.trim().toInt() }.let { (x, y) -> Point(x, y) } }
        val rectangles = points.flatMap { p -> points.map { q -> Rectangle(p,  q) } }
        
        rectangles.sorted().reversed().first().area.also { println("part 1: $it") }
        
        val polygon = Polygon.fromPoints(points)
        
//        println(rectangles.size)

        rectangles.sorted().reversed().first { it.isWithin(polygon) }.printIt()
        
//        rectangles.filter { it.isWithin(polygon) }
//            .also { it.size.printIt() }
//            .also { it.printIt() }
//            .sorted().reversed().first().printIt().area.also { println("part 2: $it") }

//        Rectangle(Point(2,3), Point(9, 5)).isWithin(polygon).printIt()
//        Rectangle(Point(2,5), Point(11, 1)).isWithin(polygon).printIt()
//        Rectangle(Point(2,3), Point(11, 7)).isWithin(polygon).printIt()

    }
}

// 7,1
//..............
//.......#XXX#..
//.......XXXXX..
//..OOOOOOOOXX..
//..OOOOOOOOXX..
//..OOOOOOOOXX..
//.........XXX..
//.........#X#..
//..............
