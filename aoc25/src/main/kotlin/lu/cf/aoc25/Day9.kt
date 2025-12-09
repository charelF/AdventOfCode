package lu.cf.aoc25

import lu.cf.aoc25.Day9.Rectangle.Companion.contains
import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt
import java.util.Queue
import kotlin.math.max
import kotlin.math.min

object Day9 {
    val reader = Reader(9)
    
    data class Point(val x: Int, val y: Int)
    
    data class Rectangle (val p1: Point, val p2: Point): Comparable<Rectangle> {
        val minX = min(p1.x, p2.x)
        val minY = min(p1.y, p2.y)
        val maxX = 
        val tr: Pair<Int, Int> = Pair(max(p1.x, p2.x), max(p1.y, p2.y))
        val br: Pair<Int, Int> = Pair(max(p1.x, p2.x), min(p1.y, p2.y))
        val bl: Pair<Int, Int> = Pair(min(p1.x, p2.x), min(p1.y, p2.y))
        val area: Long = (tr.first - bl.first + 1L) * (tl.second - bl.second + 1L)

        override fun compareTo(other: Rectangle) = this.area.compareTo(other.area)

        override fun toString() = "(${p1.first}, ${p1.second}) - (${p2.first}, ${p2.second}) - A=$area"
        
        private fun getAllPoints(): Set<Pair<Int, Int>> {
            return (bl.first .. tr.first).flatMap { i -> 
                (bl.second .. tr.second).map { j -> 
                    i to j
                }
            }.toSet()
        }
        
        companion object {
            var i = 0
            operator fun MutableSet<Pair<Int, Int>>.contains(rectangle: Rectangle): Boolean {
                if (i++ % 1000 == 0) println(i)
                return rectangle.getAllPoints().all { it in this }
            }
        }
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
    
    fun buildRedGreenShape(points: List<Pair<Int, Int>>): MutableSet<Pair<Int, Int>> {
        val bottomEdge = points.minBy { it.second }.second - 1
        val rightEdge = points.maxBy { it.first }.first + 1
        val leftEdge = points.minBy { it.first }.first - 1
        val topEdge = points.maxBy { it.second }.second + 1
        
        println("bottomEdge: $bottomEdge")
        println("rightEdge: $rightEdge")
        println("leftEdge: $leftEdge")
        println("topEdge: $topEdge")
        
        val border = buildBorder(points)
        val pointsOutsideBorder: MutableSet<Pair<Int, Int>> = mutableSetOf()
        val redGreenShape: MutableSet<Pair<Int, Int>> = mutableSetOf()
        
        var i = 0
        println("area: ${Rectangle(rightEdge to topEdge, leftEdge to bottomEdge).area}")
        val queue: MutableList<Pair<Int, Int>> = mutableListOf(rightEdge to topEdge)
        while (queue.isNotEmpty()) {
            i ++
            if (i % 100000 == 0) {
                println("outside: ${pointsOutsideBorder.size}, queue: ${queue.size}")
            }
            val e = queue.removeFirst()
            pointsOutsideBorder.add(e)
//            println(e)
            val nbs = e.getNeighbours()
//                .printIt()
                .filter { it.first <= rightEdge && it.second <= topEdge && it.first >= leftEdge && it.second >= bottomEdge }
//                .printIt()
                .filter { it !in border }
//                .printIt()
                .filter { it !in pointsOutsideBorder }
//                .printIt()
                .filter { it !in queue }
            queue.addAll(nbs)
        }
        
        for (i in leftEdge .. rightEdge) {
            for (j in bottomEdge .. topEdge) {
                if ((i to j) !in pointsOutsideBorder) redGreenShape.add(i to j)
            }
        }
        
        return redGreenShape
    }

    fun buildRedGreenShape2(points: List<Pair<Int, Int>>): MutableSet<Pair<Int, Int>> {
        val bottomEdge = points.minBy { it.second }.second - 1
        val rightEdge = points.maxBy { it.first }.first + 1
        val leftEdge = points.minBy { it.first }.first - 1
        val topEdge = points.maxBy { it.second }.second + 1
        
        val vc = (rightEdge + leftEdge) / 2
        val border = buildBorder(points)
        
        var start: Pair<Int, Int>? = null

        var outside = true
        for (i in bottomEdge .. topEdge) {
            
            if ((i to vc) in border) {
                outside = !outside
                continue
            }
            if (!outside) {
                start = i to vc
                break
            }
        }
        println("found point in shape: $start")

        val redGreenShape: MutableSet<Pair<Int, Int>> = mutableSetOf()

        var i = 0
        val queue: MutableList<Pair<Int, Int>> = mutableListOf(rightEdge to topEdge)
        while (queue.isNotEmpty()) {
            i ++
            if (i % 1000 == 0) {
                println("redGreenShape: ${redGreenShape.size}, queue: ${queue.size}")
            }
            val e = queue.removeFirst()
            redGreenShape.add(e)
            val nbs = e.getNeighbours()
                .filter { it.first <= rightEdge && it.second <= topEdge && it.first >= leftEdge && it.second >= bottomEdge }
//                .printIt()
                .filter { it !in border }
//                .printIt()
                .filter { it !in queue }
            queue.addAll(nbs)
        }
        
        return redGreenShape
    }
        
    
    fun main() { 
        val input = reader.samples().first().readLines()
//         val input = reader.actual().readLines()
        
        val points = input.map { line -> line.split(",").map { it.trim().toInt() }.let { (x, y) -> x to y } }
        val rectangles = points.flatMap { p -> points.map { q -> Rectangle(p,  q) } }
        
        rectangles.sorted().reversed().first().area.printIt()

        println("p2")
//        buildBorder(points).printIt()
        
        println("size: ${rectangles.size}")

        val shape = buildRedGreenShape2(points)
        println("shape size: ${shape.size}")
//        val filtered = rectangles.filter { shape.contains(it) }

//        println(filtered.size)
        
        

        rectangles.sorted().reversed().first { shape.contains(it) }.area.printIt()

//        filtered.sorted().reversed().first().area.printIt()
    }
}


//..............
//.......#XXX#..
//.......XXXXX..
//..OOOOOOOOXX..
//..OOOOOOOOXX..
//..OOOOOOOOXX..
//.........XXX..
//.........#X#..
//..............
