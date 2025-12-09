package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt
import kotlin.math.max
import kotlin.math.min

object Day9 {
    val reader = Reader(9)
    
    data class Point(val x: Int, val y: Int) {
        fun getMegaPoints(): Set<Point> {
            return setOf(
                Point(x-1, y-1),
                Point(x, y-1),
                Point(x+1, y-1),

                Point(x-1, y),
                Point(x+1, y),

                Point(x-1, y+1),
                Point(x, y+1),
                Point(x+1, y+1)
            )
        }
        
        fun isWithinPolygon(polygon: MegaPolygon): Boolean {
            if (this in polygon.border) return true
            var flips = 0
            for (x in this.x..polygon.maxX) {
                val p = Point(x, this.y)
                val p2 = Point(x + 1, this.y)
                if ((p in polygon.border) && (p2 !in polygon.border)) flips++
                println("x: $x")
                println("  p: $p - in border: ${p in polygon.border}")
                println("  p2: $p2 - in border: ${p2 in polygon.border}")
                println("flips: $flips")
            }
            // If the number of intersections is odd, the point is inside the polygon.
            val z = flips % 2 == 1
            return z
        }
    }
    
//    data class Polygon(val border: MutableSet<Point>) {
//        val minX = border.minBy { it.x }.x - 1
//        val minY = border.minBy { it.y }.y - 1
//        val maxX = border.maxBy { it.x }.x + 1
//        val maxY = border.maxBy { it.y }.y + 1
        
//        companion object {
//            fun fromPoints(points: List<Point>): Polygon {
//                val border: MutableSet<Point> = mutableSetOf()
//                for (i in 0..points.size) {
//                    val p1 = points[i % points.size]
//                    val p2 = points[(i + 1) % points.size]
//                    (min(p1.x, p2.x)..max(p1.x, p2.x)).forEach { border.add(Point(it, p1.y)) }
//                    (min(p1.y, p2.y)..max(p1.y, p2.y)).forEach { border.add(Point(p1.x, it)) }
//                }
//                return Polygon(border)
//            }
//        }
        
    
    data class MegaPolygon(val points: List<Point>) {
        val border: MutableSet<Point>
        val maxX: Int
        val minX: Int
        val maxY: Int
        val minY: Int
        var megaPoints: Set<Point>? = null
        
        init {
            border = mutableSetOf()
            for (i in 0..points.size) {
                val p1 = points[i % points.size]
                val p2 = points[(i + 1) % points.size]
                (min(p1.x, p2.x)..max(p1.x, p2.x)).forEach { border.add(Point(it, p1.y)) }
                (min(p1.y, p2.y)..max(p1.y, p2.y)).forEach { border.add(Point(p1.x, it)) }
            }
            minX = border.minBy { it.x }.x - 2
            minY = border.minBy { it.y }.y - 2
            maxX = border.maxBy { it.x }.x + 2
            maxY = border.maxBy { it.y }.y + 2
            megaPoints = points.flatMap { it.getMegaPoints() }.toSet()
        }
        
        
        var gigaPoints: Set<Point>? = null
        fun getGigaPoints() {
            gigaPoints = megaPoints!!.filter { !it.isWithinPolygon(this) }.toSet()
        }
        
        override fun toString(): String {
            val arr = Array(maxY) { CharArray(maxX) {'.'} }
            points.forEach { arr[it.y][it.x] = 'x' }
            megaPoints?.forEach { arr[it.y][it.x] = '@' }
            gigaPoints?.forEach { arr[it.y][it.x] = '*' }
            return arr.joinToString(separator = "\n") { it.joinToString(separator = "") }
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
        
        private fun getBorders(): Set<Point> {
            val acc = mutableSetOf<Point>()
            for (x in minX .. maxX) {
                acc.add(Point(x, minY))
                acc.add(Point(x, maxY))
            }
            for (y in minY .. maxY) {
                acc.add(Point(minY, y))
                acc.add(Point(maxY, y))
            }
            return acc//.also { println("acc: ${it.size}") }
        }
        
        /** Ray Casting Algorithm **/
        fun isWithin(polygon: MegaPolygon): Boolean {
//            return getAllPoints()
                return getBorders().none { it in polygon.gigaPoints!! }
//                println(point)
//                if (point in polygon.border) true
//                else {
//                    var flips = 0
////                    println("polygonmaxX: ${polygon.maxX}")
//                    for (x in point.x..polygon.maxX) {
//                        val p = Point(x, point.y)
//                        val p2 = Point(x + 1, point.y)
////                        println("  p: $p - in border: ${p in polygon.border}")
////                        println("  p2: $p2 - in border: ${p2 in polygon.border}")
//                        if ((p in polygon.border) && (p2 !in polygon.border)) flips++
////                        println("  flips: $flips")
//                        
//                    }
//                    // If the number of intersections is odd, the point is inside the polygon.
//                    val z = flips % 2 == 1
//                    println("flips: $flips")
//                    println(polygon.border)
//                    println(z)
//                    println("--------")
//                    z
//                }
        }
    }
    
    fun main() { 
//        val input = reader.samples().first().readLines()
         val input = reader.actual().readLines()
        
        val points = input.map { line -> line.split(",").map { it.trim().toInt() }.let { (x, y) -> Point(x, y) } }
        val rectangles = points.flatMap { p -> points.map { q -> Rectangle(p,  q) } }
        
        rectangles.sorted().reversed().first().area.also { println("part 1: $it") }
        
        val polygon = MegaPolygon(points)
        
        println(polygon)
        
        println("-> ${Point(6, 1).isWithinPolygon(polygon)}")
        
        return
        
        polygon.getGigaPoints()
        println("polygon: ${polygon.border.size}")
        println("gigaPoints: ${polygon.gigaPoints}")
        
        
//        println(rectangles.size)
        
        val sortedRectangles = rectangles.sorted().reversed()
        var i = 0
        for (rectangle in sortedRectangles) {
            i++
            if (i%1 == 0) println(i)
            if (rectangle.isWithin(polygon)) {
                println("part 2: ${rectangle.area}")
                break
            }
        }
        

//        .first { it.isWithin(polygon) }.printIt()
        
        rectangles.filter { it.isWithin(polygon) }
            .also { it.size.printIt() }
            .also { it.printIt() }
            .sorted().reversed().first().printIt().area.also { println("part 2: $it") }

//        Rectangle(Point(2,3), Point(9, 5)).isWithin(polygon).printIt()
//        Rectangle(Point(2,5), Point(11, 1)).isWithin(polygon).printIt()
//        Rectangle(Point(2,3), Point(11, 7)).isWithin(polygon).printIt()

    }
}

// 7,1
//..............
//.......#XXX#..
//.......XXXXX..
//..OOO000OOXX..
//..OOOXXXOOXX..
//..OOOX.XOOXX..
//.........XXX..
//.........#X#..
//..............
