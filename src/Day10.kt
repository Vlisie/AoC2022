import java.io.File
import java.lang.IllegalArgumentException

class Day10(file: File) {
    private val instructionList = file.readLines()
        .map {
            val s = it.split(" ")
            Instruction(s[0], s.getOrNull(1)?.toInt())
        }
    val signalStrengthList = mutableListOf<Int>()
    var x = 1
    var cycles = 0;
    val thRegister = arrayOf(20, 60, 100, 140, 180, 220)

    fun solvePart1(): Int {
        return doorloopInstructie()
    }

    fun solvePart2(): Int {
        return 0
    }

    private fun doorloopInstructie(): Int {
        instructionList.forEach {
            voegCyclesToe(it)
        }
        return signalStrengthList.sum()
    }

    fun voegCyclesToe(instructie: Instruction) {
        checkAndUpdateSignalStrength()
        when (instructie.instructie) {
            "noop" -> cycles += 1
            "addx" -> {
                cycles += 1
                checkAndUpdateSignalStrength()
                cycles += 1
                x += instructie.waarde!!
            }
            else -> {
                throw IllegalArgumentException("Instructie String klopt niet.")
            }
        }
    }

    private fun checkAndUpdateSignalStrength() {
        if(thRegister.contains(cycles + 1)) {
            signalStrengthList.add((cycles +  1) * x)
        }
    }
}

data class Instruction(val instructie: String, val waarde: Int?)
