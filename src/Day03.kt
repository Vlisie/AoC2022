import java.io.File

fun main() {

    fun commonChar(s1: String, s2: String): Char {
        for (i in s1.indices) {
            for (element in s2) {
                if (s1[i] == element) {
                    return s1[i]
                }
            }
        }
        return ' '
    }

    fun commonChar2(s1: String, s2: String, s3: String): Char {
        for (i in s1.indices) {
            for (element in s2) {
                if (s1[i] == element) {
                    for (element1 in s3) {
                        if (s1[i] == element1) {
                            return s1[i]
                        }
                    }
                }
            }
        }
        return ' '
    }

    fun valueOfChar(commonChar: Char): Int {
        return if (commonChar.isLowerCase()) {
            commonChar.code - 96
        } else {
            commonChar.code - 38
        }
    }

    fun part1(file: File): Int = file
        .readText()
        .trimEnd()
        .split("\n".toRegex())
        .map {
            val helft = it.length / 2
            it.split("(?<=\\G.{$helft})".toRegex()).toTypedArray()
        }
        .map { element ->
            valueOfChar(commonChar(element[0], element[1]))
        }
        .sum()

    fun part2(file: File): Int {
        val lijst = ArrayList<String>()
        var som = 0
        file.readText()
            .trimEnd()
            .split("\n".toRegex())
            .mapIndexed { idx, element ->
                if ((idx + 1) % 3 == 0) {
                    lijst.add(element)
                    som += valueOfChar(commonChar2(lijst[0], lijst[1], lijst[2]))
                    lijst.clear()
                } else {
                    lijst.add(element)
                }
            }
        return som
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src", "input/testInput.txt")
    check(part2(testInput) == 70)

    val input = File("src", "input/input3.txt")
    println(part2(input))
}
