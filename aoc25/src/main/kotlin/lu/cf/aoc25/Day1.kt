package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println


object Day1 {
    val reader = Reader(1)


    fun main() {
//        part1(reader.samples().first())
        part1(reader.actual())
    }

    fun part1(lines: List<String>) {
        var total = 50
        var zeroCount = 0
        var zeroCount2 = 0
//        .forEach { line ->
        lines.forEach { line ->
            val rot = line.first()
            val num = line.drop(1).toInt()
            println("$rot $num")
            when (rot) {
                'L' -> total -= num
                'R' -> total += num
            }
            while (total < 0) {
                total += 100
                zeroCount2++
            }
            while (total >= 100) {
                total -= 100
                zeroCount2++
            }
            if (total == 0) zeroCount++
            println(total)
            println("--")
        }
        println("total: $total")
        println("zeroCount2: $zeroCount2")
        println("zeroCount: $zeroCount")
        println(zeroCount + zeroCount2)
    }
}

//6911 too high


//The dial starts by pointing at 50.
//The dial is rotated L68 to point at 82; during this rotation, it points at 0 once.
//The dial is rotated L30 to point at 52.
//The dial is rotated R48 to point at 0.
//The dial is rotated L5 to point at 95.
//The dial is rotated R60 to point at 55; during this rotation, it points at 0 once.
//The dial is rotated L55 to point at 0.
//The dial is rotated L1 to point at 99.
//The dial is rotated L99 to point at 0.
//The dial is rotated R14 to point at 14.
//The dial is rotated L82 to point at 32; during this rotation, it points at 0 once.