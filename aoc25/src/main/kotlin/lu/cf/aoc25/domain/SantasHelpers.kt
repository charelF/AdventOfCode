package lu.cf.aoc25.domain


fun <T> T.println(): T {
    println(this)
    return this
}

fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}