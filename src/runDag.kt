import java.io.File

fun main() {
// test if implementation meets criteria from the description, like:
    val testInput = File("src/input", "testInput.txt")
    val dag10test = Day10(testInput)
    val input = File("src/input", "input10.txt")
    val day10 = Day10(input)
    println("Part 1 ----------- TEST")
    val test1 = dag10test.solvePart1()
    println(test1)
    check(test1 == 13140)
    println("Part 1 -----------")
    println(day10.solvePart1())


   println("Part 2 ----------- TEST")
    val test2 = dag10test.solvePart2()
    println(test2)
    //check(test2 == 36)
    println("Part 2 -----------")
    println(day10.solvePart2())
}