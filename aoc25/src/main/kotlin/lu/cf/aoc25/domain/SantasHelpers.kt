package lu.cf.aoc25.domain


fun <T> T.println(): T {
    println(this)
    return this
}