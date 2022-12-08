import java.io.File

fun main() {

    fun part1(file: File): Int {
        var visibleTrees = 0
        val grid = file.readLines()
            .map { string ->
                string.chunked(1)
                    .map { it.toInt() }
            }

        //Outer trees
        visibleTrees += (2 * (grid.size + grid[0].size) - 4)

        //-1 om buitenste ring te skippen
        for (rowTree in 1 until grid.size - 1) {
            for (colTree in 1 until grid[rowTree].size - 1) {
                val treeHeight = grid[rowTree][colTree]
                val treesHeightLeft = grid[rowTree].slice(0 until colTree).max()
                val treesHeightRight = grid[rowTree].slice(colTree + 1 until grid[rowTree].size).max()
                val treesHeightUp = grid.slice(0 until rowTree).map { it[colTree] }.max()
                val treesHeightDown = grid.slice(rowTree + 1 until grid.size).map { it[colTree] }.max()
                if (treeHeight > treesHeightLeft ||
                    treeHeight > treesHeightRight ||
                    treeHeight > treesHeightUp ||
                    treeHeight > treesHeightDown
                ) {
                    visibleTrees++
                }
            }
        }
        return visibleTrees
    }

    fun part2(file: File): Int {
        var scenicScore = 0
        val grid = file.readLines()
            .map { string ->
                string.chunked(1)
                    .map { it.toInt() }
            }

        //gestolen functie
        fun List<List<Int>>.viewFrom(x: Int, y: Int): List<List<Int>> {
            return listOf(
                (y - 1 downTo 0).map { this[it][x] }, // Up
                (y + 1 until grid.size).map { this[it][x] }, // Down
                this[y].take(x).asReversed(), // Left
                this[y].drop(x + 1) // Right
            )
        }

        //gestolen functie
        fun List<List<Int>>.scoreAt(x: Int, y: Int): Int =
            viewFrom(x, y).map { direction ->
                direction.takeUntil { it >= this[y][x] }.count()
            }.product()

        scenicScore = (1 until grid.size - 1).maxOf { y ->
            (1 until grid[0].size - 1).maxOf { x ->
                grid.scoreAt(x, y)
            }
        }
        //-1 om buitenste ring te skippen
       /* for (rowTree in 1 until grid.size - 1) {
            for (colTree in 1 until grid[rowTree].size - 1) {
                grid.scoreAt(colTree, rowTree)

                val treeHeight = grid[rowTree][colTree]

                val scenicLengthLeft = grid[rowTree].slice(0 until colTree)
                    .reversed()
                    .takeWhile { it < treeHeight }.count()
                val scenicLengthRight = grid[rowTree].slice(colTree + 1 until grid[rowTree].size)
                    .takeWhile { it < treeHeight }.count()
                val scenicLengthUp = grid.slice(0 until rowTree)
                    .map { it[colTree] }.reversed()
                    .takeWhile { it < treeHeight }.count()
                val scenicLengthDown = grid.slice(rowTree + 1 until grid.size)
                    .map { it[colTree] }
                    .takeWhile { it < treeHeight }.count()

                val score = scenicLengthLeft * scenicLengthRight * scenicLengthUp * scenicLengthDown
                if (scenicScore < score) {
                    scenicScore = score
                }
            }
        }*/
        return scenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/input", "testInput.txt")
    println("Part 1 ----------- TEST")
    val test1 = part1(testInput)
    println(test1)
    check(test1 == 21)

    println("Part 2 ----------- TEST")
    val test2 = part2(testInput)
    println(test2)
    check(test2 == 8)

    val input = File("src/input", "input8.txt")
    println("Part 1 -----------")
    println(part1(input))
    println("Part 2 -----------")
    println(part2(input))

}