import java.io.File

class Day09(file: File) {
    var head: Coordinate = Coordinate(0, 0)
    val tail: Coordinate = Coordinate(0, 0)
    val tailArray = arrayOf(Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0),Coordinate(0, 0))
    val tailList = mutableListOf<Coordinate>()
    val motionList = file.readLines()
        .map { Motion(Direction.withFirstLetter(it[0]), it.substring(2).toInt()) }

    fun part1(): Int {
        moveHead(false)
        return tailList.distinct().count()
    }

    fun part2(): Int {
        tailList.clear()
        moveHead(true)
        return tailList.distinct().count()
    }

    private fun updateTail() {
        val diffx = head.x - tail.x
        val diffy = head.y - tail.y
        if (diffx == 0 || diffy == 0) {
            if (diffx < -1 || diffx > 1) {
                tail.addToX(diffx / 2)
            } else if (diffy < -1 || diffy > 1) {
                tail.addToY(diffy / 2)
            }
            tailList.add(Coordinate(tail.x,tail.y))
        } else {
            if (diffx < -1 || diffx > 1) {
                tail.addToX(diffx / 2)
                tail.addToY(diffy)
            } else if (diffy < -1 || diffy > 1) {
                tail.addToY(diffy / 2)
                tail.addToX(diffx)
            }
            tailList.add(Coordinate(tail.x,tail.y))
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
    }

    private fun moveHead(part2: Boolean) {
        motionList.map { motion ->
            when (motion.direction) {
                Direction.RIGHT -> (1..motion.steps).map {
                    if(part2){
                        tailArray[0].addToX(1)
                        updateTails()
                    }else{
                        head.addToX(1)
                        updateTail()
                    }
                }
                Direction.LEFT -> (1..motion.steps).map {
                    if(part2){
                        tailArray[0].addToX(-1)
                        updateTails()
                    }else{
                        head.addToX(-1)
                        updateTail()
                    }
                }
                Direction.UP -> (1..motion.steps).map {
                    if(part2){
                        tailArray[0].addToY(1)
                        updateTails()
                    }else{
                        head.addToY(1)
                        updateTail()
                    }
                }
                Direction.DOWN -> (1..motion.steps).map {
                    if(part2){
                        tailArray[0].addToY(-1)
                        updateTails()
                    }else{
                        head.addToY(-1)
                        updateTail()
                    }
                }
            }
        }
    }
}

enum class Direction(val firstLetter: Char) {
    RIGHT('R'), LEFT('L'), UP('U'), DOWN('D');

    companion object {
        fun withFirstLetter(value: Char) = Direction.values().first { it.firstLetter == value }
    }
}

data class Coordinate(var x: Int, var y: Int) {
    fun addToX(int: Int) {
        x += int
    }

    fun addToY(int: Int) {
        y += int
    }
}

data class Motion(val direction: Direction, val steps: Int)