package lu.cf.aoc25

import lu.cf.aoc25.domain.Reader
import lu.cf.aoc25.domain.printIt

object Day7 {
    val reader = Reader(7)

    fun main() {
         val input = reader.actual().readLines()
//        val input = reader.samples().first().readLines()
        p1(input)
    }

    private fun p1(input: List<String>) {
        val lines = input.map { it.toMutableList() }
        var splits = 0
        for (i in 1..<lines.size) {
            val a = i - 1
            for (j in 0..<lines.first().size) {
                when {
                    lines[a][j] == 'S' -> lines[i][j] = '|'
                    lines[i][j] == '^' && lines[a][j] == '|' -> {
                        lines[i][j - 1] = '|'
                        lines[i][j + 1] = '|'
                    }
                    lines[a][j] == '|' -> lines[i][j] = '|'
                }
                if (lines[a][j] == '|' && lines[i][j] == '^') splits++
            }
        }
        println(splits)

        // lines.map { it.joinToString(separator = "").printIt() }
        
        val possibilities = MutableList(lines.size) { MutableList(lines.first().size) { 0L } }
        for (i in 0..<lines.size - 1) {
            for (j in 0..<lines.first().size) {
                when(lines[i][j]) {
                    'S' -> possibilities[i+1][j] = 1L
                    '|' -> when(lines[i+1][j]) {
                        '^' -> {
                            possibilities[i+1][j+1] += possibilities[i][j]
                            possibilities[i+1][j-1] += possibilities[i][j]
                        }
                        else -> possibilities[i+1][j] += possibilities[i][j]
                    }
                }
            }
        }
        
        println(possibilities.last().sum())
    }
}

/*

.......S.......
.......|....... 1 path
......|^|...... split -> 2 paths
......|.|...... 2 paths
.....|^|^|..... each path split again twice. total paths = 4
.....|.|.|..... 4 paths (two overlap) -> each gets split -> 2 * 2 * 2
....|^|^|^|....
....|.|.|.|.... 8 paths (multiple overlap, 3 beams total)
...|^|^|||^|... 
...|.|.|||.|...
..|^|^|||^|^|..
..|.|.|||.|.|..
.|^|||^||.||^|.
.|.|||.||.||.|.
|^|^|^|^|^|||^|
|.|.|.|.|.|||.|


.......S.......
.......|....... the beam has 1 way to end up there
......|^|...... 
......|.|...... left bream has 1 way to be there, right beam 1 way
.....|^|^|..... left beam: parent has 1 way -> 1 way. right beam: parent has 1 way -> 1 way
.....|.|.|.....      middle beam: two parents: -> two ways to
....|^|^|^|.... left beam: 1 parent. 2nd beam: 1 parent with 1 parent, 1 parent with 2 parents -> 3 ways to end up there
....|.|.|.|.... -> so a childs possibilities become the possibilities of its parents, like in the real world
...|^|^|||^|... 
...|.|.|||.|...
..|^|^|||^|^|..
..|.|.|||.|.|..
.|^|||^||.||^|.
.|.|||.||.||.|.
|^|^|^|^|^|||^|
|.|.|.|.|.|||.|

 */