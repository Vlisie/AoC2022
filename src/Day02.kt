import java.io.File

//rock a en x paper b en y scissor c en z
fun main() {

    fun isElfWinner(opponent: String, elf: String): Boolean {
        return opponent == "A" && elf == "Y" || opponent == "B" && elf == "Z" || opponent == "C" && elf == "X"
    }

    fun isDraw(opponent: String, elf: String): Boolean {
        return opponent == "A" && elf == "X" || opponent == "B" && elf == "Y" || opponent == "C" && elf == "Z"
    }

    fun keuzeWaarde(elf: String): Int {
        return when (elf) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }
    }

    fun makeLose(opponent: String): String {
        return when (opponent) {
            "A" -> "Z"
            "B" -> "X"
            "C" -> "Y"
            else -> ""
        }
    }

    fun makeDraw(opponent: String): String {
        return when (opponent) {
            "A" -> "X"
            "B" -> "Y"
            "C" -> "Z"
            else -> ""
        }
    }

    fun makeWin(opponent: String): String {
        return when (opponent) {
            "A" -> "Y"
            "B" -> "Z"
            "C" -> "X"
            else -> ""
        }
    }

    fun choosePlay(opponent: String, winOrLoose: String): Int {
        return when (winOrLoose) {
            "X" -> keuzeWaarde(makeLose(opponent))
            "Y" -> keuzeWaarde(makeDraw(opponent)) + 3
            "Z" -> keuzeWaarde(makeWin(opponent)) + 6
            else -> 0
        }
    }

    fun part1(): Int = File("src", "input2.txt")
        .readText()
        .trimEnd()
        .split("\n".toRegex())
        .map {
            it.split("\\s".toRegex()).toTypedArray()
        }
        .map { element ->
            if (isElfWinner(element[0], element[1])) {
                return@map 6 + keuzeWaarde(element[1])
            } else if (isDraw(element[0], element[1])) {
                return@map 3 + keuzeWaarde(element[1])
            } else {
                return@map 0 + keuzeWaarde(element[1])
            }
        }
        .sum()

    fun part2(file: File): Int = file
        .readText()
        .trimEnd()
        .split("\n".toRegex())
        .map {
            it.split("\\s".toRegex()).toTypedArray()
        }
        .map { element ->
            return@map choosePlay(element[0], element[1])
        }
        .sum()

    // test if implementation meets criteria from the description, like:
    val testInput = File("src", "testInput.txt")
    check(part2(testInput) == 12)

    val input = File("src", "input2.txt")
    println(part2(input))
}
