import java.io.File
import java.util.stream.IntStream

fun main() {
    val parentDir = Dir("/", mutableListOf())

    fun findPreviousDir(pathArray: ArrayList<String>): Dir {
        var currentDir = parentDir
        for (i in 1 until pathArray.size) {
            (currentDir.items.find { it.name == pathArray[i] } as Dir).also { currentDir = it }
        }
        return currentDir
    }

    fun findTotalOfSmallEnoughDir(): Int {
        var totalSizeOfSelectDirs = 0
        for (dir in parentDir.items) {
            if (dir is Filetje) continue
            if (dir.grootte <= 100000) {
                totalSizeOfSelectDirs += dir.grootte
            }
            if (dir is Filetje) continue
            dir as Dir
            for (dir2 in dir.items) {
                if (dir2 is Filetje) continue
                if (dir2.grootte <= 100000) {
                    totalSizeOfSelectDirs += dir2.grootte
                }
                if (dir2 is Filetje) continue
                dir2 as Dir
                for (dir3 in dir2.items) {
                    if (dir3 is Filetje) continue
                    if (dir3.grootte <= 100000) {
                        totalSizeOfSelectDirs += dir3.grootte
                    }
                    dir3 as Dir
                    for (dir4 in dir3.items) {
                        if (dir4 is Filetje) continue
                        if (dir4.grootte <= 100000) {
                            totalSizeOfSelectDirs += dir4.grootte
                        }
                        dir4 as Dir
                        for (dir5 in dir4.items) {
                            if (dir5 is Filetje) continue
                            if (dir5.grootte <= 100000) {
                                totalSizeOfSelectDirs += dir5.grootte
                            }
                            dir5 as Dir
                            for (dir6 in dir5.items) {
                                if (dir6 is Filetje) continue
                                if (dir6.grootte <= 100000) {
                                    totalSizeOfSelectDirs += dir6.grootte
                                }
                                dir6 as Dir
                                for (dir7 in dir6.items) {
                                    if (dir7 is Filetje) continue
                                    if (dir7.grootte <= 100000) {
                                        totalSizeOfSelectDirs += dir7.grootte
                                    }
                                    dir7 as Dir
                                    for (dir8 in dir7.items) {
                                        if (dir8 is Filetje) continue
                                        if (dir8.grootte <= 100000) {
                                            totalSizeOfSelectDirs += dir8.grootte
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return totalSizeOfSelectDirs
    }

    fun part1(file: File): Int {
        var currentDir = parentDir
        val currentPathArray = arrayListOf(currentDir.name)
        file.readText()
            .split("\n".toRegex())
            .map {
                if (it.startsWith("$")) {
                    if (it.contains("$ cd")) {
                        if (it == "$ cd ..") {
                            //level omhoog
                            currentPathArray.removeAt(currentPathArray.size - 1)
                            currentDir = findPreviousDir(currentPathArray)
                        } else {
                            //Altijd eerste actie
                            val dirName = it.substring(5)
                            currentDir = currentDir.items.find { it.name == dirName } as Dir
                            currentPathArray.add(currentDir.name)
                        }
                    } else {
                        // list items command, geen actie verder
                    }
                } else if (it.startsWith("dir")) {
                    //add dir on this level, don't go into it
                    val dir = Dir(it.substring(4), mutableListOf())
                    currentDir.items.add(dir)

                } else if (it[0].isDigit()) {
                    //add file with name, size on this level
                    val indexOfSpace = it.indexOf(" ")
                    val size = it.substring(0, indexOfSpace).toInt()
                    val name = it.substring(indexOfSpace + 1)
                    currentDir.items.add(Filetje(name, size))
                } else {
                    println("Ergens gaat iets fout, String waarde niet herkend!")
                }
            }
        return findTotalOfSmallEnoughDir()
    }

    /*    fun part2(file: File): Int {
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
        }*/

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/input", "testInput.txt")
    check(part1(testInput) == 95437)

    //check(part2(testInput) == 19)

    val input = File("src/input", "input7.txt")
    println("Part 1 -----------")
    check(part1(input) == 1454188)
    println(part1(input))
    println("Part 2 -----------")
    //println(part2(input))

}

interface Bestand {
    val name: String
    val grootte: Int
}

data class Dir(
    override val name: String,
    val items: MutableList<Bestand>
) : Bestand {
    override val grootte: Int
        get() = items.stream().flatMapToInt { IntStream.of(it.grootte) }.sum()
}

data class Filetje(
    override val name: String, override val grootte: Int
) : Bestand
