import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day09(file: File) {
    val tailList = mutableListOf<Coordinate>()
    val motionList = file.readLines()
        .map { Motion(Direction.withFirstLetter(it[0]), it.substring(2).toInt()) }

    fun part1(): Int {
        return moveHead(2)
    }

    fun part2(): Int {
        tailList.clear()
        return moveHead(10)
    }

    /*    private fun updateTail() {
        val diffx = head.x - tail.x
        val diffy = head.y - tail.y
        if (diffx == 0 || diffy == 0) {
            if (diffx < -1 || diffx > 1) {
                tail.addToX(diffx / 2)
            } else if (diffy < -1 || diffy > 1) {
                tail.addToY(diffy / 2)
            }
            tailList.add(Coordinate(tail.x, tail.y))
        } else {
            if (diffx < -1 || diffx > 1) {
                tail.addToX(diffx / 2)
                tail.addToY(diffy)
            } else if (diffy < -1 || diffy > 1) {
                tail.addToY(diffy / 2)
                tail.addToX(diffx)
            }
            tailList.add(Coordinate(tail.x, tail.y))
        }
    }

    private fun updateTails() {
        (1 until tailArray.size).forEach {
            val voorganger = tailArray[it - 1]
            val huidigeTail = tailArray[it]
            val diffx = voorganger.x - huidigeTail.x
            val diffy = voorganger.y - huidigeTail.y
            if (diffx == 0 || diffy == 0) {
                if (diffx < -1 || diffx > 1) {
                    huidigeTail.addToX(diffx / 2)
                } else if (diffy < -1 || diffy > 1) {
                    huidigeTail.addToY(diffy / 2)
                }
            } else {
                if (diffx < -1 || diffx > 1) {
                    huidigeTail.addToX(diffx / 2)
                    huidigeTail.addToY(diffy)
                } else if (diffy < -1 || diffy > 1) {
                    huidigeTail.addToY(diffy / 2)
                    huidigeTail.addToX(diffx)
                }
            }
            if (it == tailArray.lastIndex) {
                tailList.add(Coordinate(huidigeTail.x, huidigeTail.y))
            }
        }
    }*/

    private fun moveHead(knopen: Int): Int {
        val tailArray = Array(knopen) { Coordinate() }
        motionList.map { motion ->
            repeat(motion.steps) {
            tailArray[0] = tailArray[0].move(motion.direction)
            tailArray.indices.windowed(2, 1) { (head, tail) ->
                if (!tailArray[head].touches(tailArray[tail])) {
                    tailArray[tail] = tailArray[tail].moveTowards(tailArray[head])
                }
            }
            tailList += tailArray.last()
        }
    }
    return tailList.distinct().count()
}

enum class Direction(val firstLetter: Char) {
    RIGHT('R'), LEFT('L'), UP('U'), DOWN('D');

    companion object {
        fun withFirstLetter(value: Char) = Direction.values().first { it.firstLetter == value }
    }
}

data class Coordinate(var x: Int = 0, var y: Int = 0) {
    fun move(direction: Direction): Coordinate =
        when (direction) {
            Direction.RIGHT -> copy(x = x + 1)
            Direction.LEFT -> copy(x = x - 1)
            Direction.UP -> copy(y = y + 1)
            Direction.DOWN -> copy(y = y - 1)
        }

    fun touches(other: Coordinate): Boolean =
        (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1

    fun moveTowards(other: Coordinate): Coordinate =
        Coordinate(
            (other.x - x).sign + x,
            (other.y - y).sign + y
        )
}

data class Motion(val direction: Direction, val steps: Int)
}
