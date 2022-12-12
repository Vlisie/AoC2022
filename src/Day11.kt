import java.io.File
import java.lang.IllegalArgumentException

//inputbestand doorlopen en volgende waarden verzamelen voor monkeys: check
//innitial values, accumulator calculatie en test waarde.
//Dit in fold/reduce methode vullen met initial list waar accumulator wordt gebruikt. vervolgens test uitvoeren met deler getal.
//tussen inspect en test delen door 3 en naar beneden afronden
//resultaat van uitvoeringen verwerken naar einde andere monkey lists
//tel hoeveelheid turns van monkeys, twee actiefste monkeys turns vermenigvuldigen
//alle taken per aap is turn en elke aap z'n turn is een round
class Day11(file: File) {
    private val monkeyList = mutableListOf<Monkey>()

    init {
        file.readLines().forEachIndexed { index, it ->
            if (index == 0 || index % 7 == 0) {
                monkeyList.add(Monkey())
            }
            if (it.contains("Starting items")) {
                monkeyList.last().items.addAll(it.substring(18).split(", ").map { it.toLong() })
            }
            if (it.contains("Operation")) {
                monkeyList.last().formula = it.substring(19)
            }
            if (it.contains("Test")) {
                monkeyList.last().testDivisibleNumber = it.filter { it.isDigit() }.toLong()
            }
            if (it.contains("true")) {
                monkeyList.last().receiverTrue = it.filter { it.isDigit() }.toInt()
            }
            if (it.contains("false")) {
                monkeyList.last().receiverFalse = it.filter { it.isDigit() }.toInt()
            }
        }
    }

    fun solvePart1(): Long {
        val testProduct: Long = monkeyList.map { it.testDivisibleNumber }.reduce(Long::times)
        executeRounds(10000, testProduct)
        return monkeyBusiness()
    }

    private fun monkeyBusiness(): Long {
        monkeyList.sortByDescending { it.turns }
        return monkeyList[0].turns * monkeyList[1].turns
    }

    private fun executeRounds(amount: Int, operation: Long) {
        (0 until amount).forEach { _ ->
            monkeyList.forEach {
                it.items.removeAll { item ->
                    it.turns++
                    val noWorry = it.executeFormula(item) % operation
                    if (noWorry % it.testDivisibleNumber == 0L) {
                        monkeyList[it.receiverTrue].items.add(noWorry)
                    } else {
                        monkeyList[it.receiverFalse].items.add(noWorry)
                    }
                }
            }
        }
    }
}

data class Monkey(
    var items: MutableList<Long> = mutableListOf(),
    var formula: String = "",
    var testDivisibleNumber: Long = 0,
    var receiverTrue: Int = 0,
    var receiverFalse: Int = 0,
    var turns: Long = 0
) {
    fun executeFormula(item: Long): Long {
        val sign = "[/+*-]+".toRegex().find(formula)?.value
        val numberlist = formula.split(" ")
        val isdigit = numberlist.last().map { it.toChar().isDigit() }.last()
        var number = item
        if (isdigit) {
            number = numberlist.last().toLong()
        }
        return when (sign!!.trim()) {
            "+" -> item + number
            "-" -> item - number
            "*" -> item * number
            "/" -> item / number
            else -> error("Unknown operator $sign")
        }
    }

}
