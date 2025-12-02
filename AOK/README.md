# Advent of Code

## Favourites

### 2025-D2-P2 golfed

```kotlin
private fun p2v2(input: String) = input.split(',').map { range -> range.split('-').map { it.toLong() }.let { (start, end) -> (start..end).asSequence() }}.flatMap { seq -> seq.filter { element -> element.toString().let { code -> (1..code.length / 2).any { groupLength -> (code.chunked(groupLength).toSet().size == 1)}}}}.distinct().sum().also { println("part 2: $it") }
```