package lu.cf.aoc25.domain

fun <T> T.println(): T {
    println(this)
    return this
}

fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}

operator fun Boolean.plus(other: Boolean): Int {
    return when {
        this and other -> 2
        this or other -> 1
        else -> 0
    }
}

operator fun Int.plus(other: Boolean): Int {
    return this + other.toInt()
}
