import java.io.File

fun main() {

    fun uniqueCharacters(str: String): Boolean{
        val chArray = str.toCharArray()
        chArray.sort()
        for (i in 0..chArray.size - 2) {
            if (chArray[i] == chArray[i + 1]) return false else continue
        }
        return true
    }

    fun part1(file: File): Int {
        var charOfAppearance = 1
        var previous3Chars = ""
        file.readText()
            .map {
                if(!previous3Chars.contains(it) && charOfAppearance > 3 && uniqueCharacters(previous3Chars)){
                    return charOfAppearance
                }else {
                    if(charOfAppearance > 3) previous3Chars = previous3Chars.drop(1)
                    previous3Chars += it
                    charOfAppearance++
                }
            }
        return charOfAppearance
    }

    fun part2(file: File): Int {
        var charOfAppearance = 1
        var previous13Chars = ""
        file.readText()
            .map {
                if(!previous13Chars.contains(it) && charOfAppearance > 13 && uniqueCharacters(previous13Chars)){
                    return charOfAppearance
                }else {
                    if(charOfAppearance > 13) previous13Chars = previous13Chars.drop(1)
                    previous13Chars += it
                    charOfAppearance++
                }
            }
        return charOfAppearance
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/input", "testInput.txt")
    check(part1(testInput) == 7)

    check(part2(testInput) == 19)

    val input = File("src/input", "input6.txt")
    println("Part 1 -----------")
    println(part1(input))
    println("Part 2 -----------")
    println(part2(input))

}
