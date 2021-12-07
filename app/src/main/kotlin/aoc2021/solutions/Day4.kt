package aoc2021.solutions

import aoc2021.helpers.Point
import aoc2021.helpers.Resources
import aoc2021.helpers.Solution
import aoc2021.helpers.readNonEmptyLines

object Day4 : Solution {
//    val input = """
//        7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
//
//        22 13 17 11  0
//         8  2 23  4 24
//        21  9 14 16  7
//         6 10  3 18  5
//         1 12 20 15 19
//
//         3 15  0  2 22
//         9 18 13 17  5
//        19  8  7 25 23
//        20 11 10 24  4
//        14 21 16 12  6
//
//        14 21 17 24  4
//        10 16 15  9 19
//        18  8 23 26 20
//        22 11 13  6  5
//         2  0 12  3  7
//    """.trimIndent()

    val input = Resources.file("day4.txt").readText()

    fun findWinner(): WinResult {
        val sections = input.split(Regex("\r?\n\r?\n"))
        val drawings = sections.first().split(",")
        val cards = sections.drop(1).map(BingoCard::fromString)

        for (value in drawings) {
            cards.forEach { it.handleDrawing(value) }
            cards.find { it.isWinner() }?.let {
                return WinResult(it, value)
            }
        }

        error("No winner :(")
    }

    override fun part1(): String {
        val (card, drawing) = findWinner()
        return (card.unmarkedNumberSum() * drawing.toInt()).toString()
    }
}

val winningPatterns = listOf(
    // rows
    listOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0), Point(4, 0)),
    listOf(Point(0, 1), Point(1, 1), Point(2, 1), Point(3, 1), Point(4, 1)),
    listOf(Point(0, 2), Point(1, 2), Point(2, 2), Point(3, 2), Point(4, 2)),
    listOf(Point(0, 3), Point(1, 3), Point(2, 3), Point(3, 3), Point(4, 3)),
    listOf(Point(0, 4), Point(1, 4), Point(2, 4), Point(3, 4), Point(4, 4)),

    // columns
    listOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3), Point(0, 4)),
    listOf(Point(1, 0), Point(1, 1), Point(1, 2), Point(1, 3), Point(1, 4)),
    listOf(Point(2, 0), Point(2, 1), Point(2, 2), Point(2, 3), Point(2, 4)),
    listOf(Point(3, 0), Point(3, 1), Point(3, 2), Point(3, 3), Point(3, 4)),
    listOf(Point(4, 0), Point(4, 1), Point(4, 2), Point(4, 3), Point(4, 4)),
)

class BingoCard(private val squares: List<BingoSquare>) {
    companion object {
        fun fromString(string: String): BingoCard {
            val squares = string.split("\n").flatMapIndexed { rowIndex, row ->
                row.trim().split(Regex("\\s+")).mapIndexed { valueIndex, value ->
                    BingoSquare(Point(valueIndex, rowIndex), value)
                }
            }
            return BingoCard(squares)
        }
    }

    private val markedSquares = mutableSetOf<Point>()

    fun handleDrawing(value: String) {
        squares.find { it.value == value }?.let {
            markedSquares.add(it.position)
        }
    }

    fun isWinner() = winningPatterns.any { pattern ->
        markedSquares.containsAll(pattern)
    }

    fun unmarkedNumberSum(): Int =
        squares.filterNot { it.isMarked() }.sumOf { it.value.toInt() }

    private fun BingoSquare.isMarked() =
        markedSquares.contains(position)
}

data class BingoSquare(val position: Point, val value: String)

data class WinResult(val card: BingoCard, val drawing: String)
