import java.io.File

fun main() {
// test if implementation meets criteria from the description, like:
    val testInput = File("src/input", "testInput.txt")
    val dag9test = Day09(testInput)
    println("Part 1 ----------- TEST")
    val test1 = dag9test.part1()
    println(test1)
//    check(test1 == 13)

    println("Part 2 ----------- TEST")
    val test2 = dag9test.part2()
    println(test2)
    check(test2 == 36)
    //real deal
    val input = File("src/input", "input9.txt")
    val dag9 = Day09(input)
    println("Part 1 -----------")
    println(dag9.part1())
    println("Part 2 -----------")
    println(dag9.part2())
}