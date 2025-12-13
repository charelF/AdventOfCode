package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt

object Day12 {
    val reader = Reader(12)

    fun main() =
        reader.actual().readText().split("\n\n").last().split("\n").filter { line ->
            line.split(": ").let { (area, shapes) ->
                area.split("x").let { (w, h) -> w.toInt() * h.toInt() } >=
                    shapes.split(" ").fold(0) { acc, v -> acc + 9 * v.toInt() }
            }
        }.size.printIt()
}

/*

    const val SHAPE_SIZE = 2

    data class Shape(
        val points: List<Pair<Int, Int>>
    ) {
        val points90 = rot90(points)
        val points180 = rot90(points90)
        val points270 = rot90(points180)
        val area = points.size

        val rotations = listOf(
            points,
            points90,
            points180,
            points270,
        )

        private fun rot90(points: List<Pair<Int, Int>>) = points.map { (i, j) -> (SHAPE_SIZE-j) to i }

        fun getCoordinates(i: Int, j: Int): List<List<Pair<Int, Int>>> {
            return rotations.map { rot ->
                points.map { (pi, pj) -> i+pi to j+pj }
            }
        }

        override fun toString(): String {
            var s = ""
            rotations.forEach { rot ->
                s += "---\n"
                s += rot.toString()
                s += "\n"
                (0 .. SHAPE_SIZE).forEach { i ->
                    (0 .. SHAPE_SIZE).forEach { j ->
                        s += when {
                            (i to j) in rot -> '#'
                            else -> '.'
                        }
                    }
                    s += "\n"
                }
            }
            return s
        }
    }

    fun main() {
        // val input = reader.actual().readLines()
        val input = reader.samples().first().readText().split("\n\n").printIt()

        val shapes = input.dropLast(1).mapIndexed { shapeIdx, compound ->
            val points = compound.split(":\n").last().split("\n").flatMapIndexed { i, line ->
                line.mapIndexedNotNull { j, char ->
                    when (char) {
                        '#' -> i to j
                        else -> null
                    }
                }
            }
            shapeIdx to Shape(points)
        }.toList()

        shapes.first().printIt()
        // actually all of this is not needed since AOC has gone to shit
        // and this puzzle is just a waste of my intellectual capacity. It is not a fun packing
        // problem or optimisation problem, one just needs to check if they can fit area wise
        // without caring about the shapes at all?
        // This is so shit... waste of time. Solution is vibecoded in aoc25py
    }
}

 */
