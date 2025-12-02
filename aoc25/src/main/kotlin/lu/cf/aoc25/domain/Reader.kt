package lu.cf.aoc25.domain


import java.io.BufferedReader
import java.io.FileNotFoundException

class Reader(day: Int) {
    private val dayString = day.toString()

    private fun read(filename: String): BufferedReader? {
        return object {}.javaClass.getResourceAsStream("/$filename")?.bufferedReader()
    }

    fun samples(): List<BufferedReader> {
        return (1..10)
            .map { dayString + "t".repeat(it) }
            .mapNotNull { read(it) }
            .also { if (it.isEmpty()) throw FileNotFoundException(dayString + "t") }
    }

    fun actual(): BufferedReader {
        return read(dayString) ?: throw FileNotFoundException(dayString)
    }
}