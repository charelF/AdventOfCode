package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.println

object Day2 {
    val reader = Reader(1)

    fun main() {
        // val lines = reader.samples().first()
//        val input = """11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"""
        val input = """874324-1096487,6106748-6273465,1751-4283,294380-348021,5217788-5252660,828815656-828846474,66486-157652,477-1035,20185-55252,17-47,375278481-375470130,141-453,33680490-33821359,88845663-88931344,621298-752726,21764551-21780350,58537958-58673847,9983248-10042949,4457-9048,9292891448-9292952618,4382577-4494092,199525-259728,9934981035-9935011120,6738255458-6738272752,8275916-8338174,1-15,68-128,7366340343-7366538971,82803431-82838224,72410788-72501583"""
        part2(input)
    }

    fun part1(input: String) {
        var total = 0L
        input.split(',').forEach {
            val (s, e) = it.split('-')
            val start = s.toLong()
            val end = e.toLong()
//            println("$start, $end")

            (start .. end).forEach { code ->
                val c = code.toString()
                if (c.length % 2 == 0) {
                    val fh = c.take(c.length / 2)
                    val lh = c.takeLast(c.length / 2)
                    //                println("$code, $fh, $lh")
                    val idem = fh == lh
                    //                println("$idem, $c")
                    if (idem) {
//                        println(code)
                        total += code
                    }
                }
            }
        }
        println(total)
    }

    fun part2(input: String) {
//        var total = 0L
        val codes = mutableSetOf<Long>()
        input.split(',').forEach {
            val (s, e) = it.split('-')
            val start = s.toLong()
            val end = e.toLong()
            println("$start, $end")

            (start .. end).forEach { code ->
                val c = code.toString()
                val clen = c.length

                for (groupLength in (1 .. clen/2).reversed() ) {
//                    println("code: $code, groupLength: $groupLength")

                    val candidate = c.take(groupLength)
//                    println("candidate: $candidate")
                    var allmatch = true

                    for (i in groupLength ..< clen step groupLength) {
                        val e = i + groupLength
//                        println("i: $i, e: $e, e>clen: ${e>clen}")
                        if (e > clen) {
                            allmatch = false
                            break
                        }
                        if (c.substring(i, e) == candidate) continue
                        else {
                            allmatch = false
                            break
                        }
                    }
                    if (allmatch) {
//                        println("-----CODE: $code")
                        codes
                        codes.add(code)
                    }
                }
            }
            println()
        }
        println(codes)
        println(codes.sum())
    }
}

//56102315585 // too high 43952536386 correct


// p2 1227775554 too low