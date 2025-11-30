package lu.cf.aoc25.domain


import java.io.FileNotFoundException

class Reader(day: Int) {
    private val dayString = day.toString()

    private fun readLines(filename: String): List<String>? {
        return object {}.javaClass.getResourceAsStream("/$filename")?.bufferedReader()?.readLines()
    }

    fun samples(): List<List<String>> {
        return (1..10)
            .map { dayString + "t".repeat(it) }
            .mapNotNull { readLines(it) }
            .also { if (it.isEmpty()) throw FileNotFoundException(dayString + "t") }
    }

    fun actual(): List<String> {
        return readLines(dayString) ?: throw FileNotFoundException(dayString)
    }
}