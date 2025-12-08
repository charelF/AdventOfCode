package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt
import lu.cf.aoc25.domain.toInt

object Day5 {
    val reader = Reader(5)

    fun main() {
        val input = reader.actual().readText()
//        val input = reader.samples().first().readText()
//        val input = reader.samples()[2].readText()
        solve(input, 1)
//        solve(input, 2)
    }

    private fun solve(
        input: String,
        part: Int,
    ) {
        val (ranges, ids) = input.split("\n\n")

        val bounds =
            ranges.split("\n").map { range ->
                range.split('-').map { it.toLong() }
            }.toMutableList()

        // part 1
        ids.split("\n").map { it.toLong() }.sumOf { id ->
            bounds.any { (start, end) -> id in start..end }.toInt()
        }.printIt()

        println(bounds)

        val combinedBounds: MutableList<List<Long>> = mutableListOf()
        while (bounds.isNotEmpty()) {
            var (minStart, minEnd) = bounds.minBy { it.first() }.also { bounds.remove(it) }
            var prevMinEnd = -1L
            while (prevMinEnd != minEnd) {
                prevMinEnd = minEnd
                bounds.filter { (start, end) -> start <= (minEnd + 1) }
                    .also { bounds.removeAll(it) }
                    .maxByOrNull { bound -> bound.last() }
                    .also { if (it != null) minEnd = maxOf(minEnd, it.last()) }
            }
            combinedBounds.add(listOf(minStart, minEnd))
        }

        println(combinedBounds)

        combinedBounds.sumOf { (start, end) -> (end + 1) - start }.printIt()

        // 327779637831920 too low

//
//        var counter = 0L
//
//        println(bMax - bMin)
//
//        for (i in bMin .. bMax) {
//
//            if (bounds.any { (start, end) -> i in start..<end }) counter++
//        }
//        println("counter: $counter")
    }
}
