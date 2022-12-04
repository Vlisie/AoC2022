import java.io.File

fun main() {

    fun intRangeFromDashString(s: String): IntRange {
        return IntRange(s.split("-".toRegex())[0].toInt(), s.split("-".toRegex())[1].toInt())
    }

    fun compareCompleteOverlapRanges(s: String, s1: String): Int {
        val range1 = intRangeFromDashString(s)
        val range2 = intRangeFromDashString(s1)
        if (range1.first >= range2.first && range1.last <= range2.last) {
            return 1
        }
        if (range2.first >= range1.first && range2.last <= range1.last) {
            return 1
        }
        return 0
    }

    fun compareOverlapRanges(s: String, s1: String): Int {
        val range1 = intRangeFromDashString(s)
        val range2 = intRangeFromDashString(s1)
        for (nummer in range1) {
            if (range2.contains(nummer)) {
                return 1
            }
        }
        return 0
    }

    fun part1(file: File): Int = file
        .readText()
        .trimEnd()
        .split("\n".toRegex())
        .map {
            it.split(",".toRegex()).toTypedArray()
        }
        .map {
            compareCompleteOverlapRanges(it[0], it[1])
        }
        .sum()

    fun part2(file: File): Int = file
        .readText()
        .trimEnd()
        .split("\n".toRegex())
        .map {
            it.split(",".toRegex()).toTypedArray()
        }
        .map {
            compareOverlapRanges(it[0], it[1])
        }
        .sum()

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/input", "testInput.txt")
    check(part1(testInput) == 2)

    check(part2(testInput) == 4)

    val input = File("src/input", "input4.txt")
    println(part1(input))
    println("Part 2 -----------")
    println(part2(input))

}
