import java.io.File

fun main() {

    fun maakAntwoordString(stapelLijst: List<ArrayDeque<Char>>): String {
        var eindString = ""
        for (stack in stapelLijst.asIterable()) {
            eindString += if (stack.isNotEmpty()) stack.last() else ""
        }
        return eindString
    }

    fun part1(file: File): String {
        val stapelLijst: List<ArrayDeque<Char>> = List(9) { ArrayDeque() }
        file.readText()
            .split("\n".toRegex())
            .map { elem ->
                var lastOccur = 0
                for (char in elem) {
                    if (char == '[') {
                        lastOccur = elem.indexOf("[", lastOccur)
                        stapelLijst[lastOccur / 4].addFirst(elem[lastOccur + 1])
                        lastOccur++
                    }
                }
                if (elem.startsWith("move")) {
                    val cijfers = Regex("[0-9]+").findAll(elem)
                        .map(MatchResult::value)
                        .toList()
                    repeat(cijfers[0].toInt()) {
                        stapelLijst[(cijfers[2].toInt() - 1)].addLast(stapelLijst[(cijfers[1].toInt() - 1)].removeLast())
                    }
                }
            }
        return maakAntwoordString(stapelLijst)
    }

    fun part2(file: File): String {
        val stapelLijst: List<ArrayDeque<Char>> = List(9) { ArrayDeque() }
        file.readText()
            .split("\n".toRegex())
            .map { elem ->
                var lastOccur = 0
                for (char in elem) {
                    if (char == '[') {
                        lastOccur = elem.indexOf("[", lastOccur)
                        stapelLijst[lastOccur / 4].addFirst(elem[lastOccur + 1])
                        lastOccur++
                    }
                }
                if (elem.startsWith("move")) {
                    val cijfers = Regex("[0-9]+").findAll(elem)
                        .map(MatchResult::value)
                        .toList()
                    val kraanStapel = ArrayDeque<Char>()
                    repeat(cijfers[0].toInt()) {
                        kraanStapel.addFirst(stapelLijst[(cijfers[1].toInt() - 1)].removeLast())
                    }
                    repeat(kraanStapel.size) {
                        stapelLijst[(cijfers[2].toInt() - 1)].addLast(kraanStapel.removeFirst())
                    }
                }
            }
        return maakAntwoordString(stapelLijst)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/input", "testInput.txt")
    check(part1(testInput) == "CMZ")

    check(part2(testInput) == "MCD")

    val input = File("src/input", "input5.txt")
    println("Part 1 -----------")
    println(part1(input))
    println("Part 2 -----------")
    println(part2(input))

}
