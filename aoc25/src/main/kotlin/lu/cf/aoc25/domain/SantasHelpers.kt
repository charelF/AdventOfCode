package lu.cf.aoc25.domain

fun <T> T.printIt(): T {
    println(this)
    return this
}

fun Array<CharArray>.printIt(): Array<CharArray> {
    this.forEach { row ->
        row.forEach { print(it) }
        println("")
    }
    return this
}

fun Array<DoubleArray>.printIt(template: String): Array<DoubleArray> {
    this.forEach { row ->
        row.forEach { print(template.format(it)) }
        println("")
    }
    return this
}

fun Array<FloatArray>.printIt(template: String): Array<FloatArray> {
    this.forEach { row ->
        row.forEach { print(template.format(it)) }
        println("")
    }
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
