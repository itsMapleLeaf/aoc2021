package aoc2021.solutions

import aoc2021.helpers.Resources
import aoc2021.helpers.Solution
import aoc2021.helpers.readNonEmptyLines


object Day3 : Solution {
    private val inputLines = Resources.file("day3.txt").readNonEmptyLines()

//    private val inputLines = """
//        00100
//        11110
//        10110
//        10111
//        10101
//        01111
//        00111
//        11100
//        10000
//        11001
//        00010
//        01010
//    """.trimIndent().lines().filter { it.isNotEmpty() }

    private val bitPositions = 0 until inputLines.first().length

    override fun part1(): String {
        val bitCounts = bitPositions.map { bitCountsAt(it, inputLines) }
        val resultOfLeastCommonBit = bitCounts.map { it.leastCommon() }.binaryCharListToInt()
        val resultOfMostCommonBit = bitCounts.map { it.mostCommon() }.binaryCharListToInt()
        return (resultOfMostCommonBit * resultOfLeastCommonBit).toString()
    }

    override fun part2(): String {
        val resultWithMostCommonBit = let {
            var numbers = inputLines
            var position = 0

            while (numbers.size > 1 && position < bitPositions.count()) {
                val mostCommonBit = bitCountsAt(position, numbers).mostCommon()
                numbers = numbers.filter { it[position] == mostCommonBit }
                position += 1
            }

            numbers.first().toInt(2)
        }

        val resultWithLeastCommonBit = let {
            var numbers = inputLines
            var position = 0

            while (numbers.size > 1 && position < bitPositions.count()) {
                val leastCommonBit = bitCountsAt(position, numbers).leastCommon()
                numbers = numbers.filter { it[position] == leastCommonBit }
                position += 1
            }

            numbers.first().toInt(2)
        }

        return (resultWithMostCommonBit * resultWithLeastCommonBit).toString()
    }

    data class BitCounts(val zeroes: Int, val ones: Int) {
        fun mostCommon() = if (ones >= zeroes) '1' else '0'
        fun leastCommon() = if (zeroes <= ones) '0' else '1'
    }

    private fun bitCountsAt(position: Int, numbers: List<String>): BitCounts {
        val bits = numbers.map { it[position] }
        return BitCounts(bits.countOf('0'), bits.countOf('1'))
    }

    private fun <T> List<T>.countOf(value: T) =
        count { it == value }

    private fun List<Char>.binaryCharListToInt() =
        joinToString("").toInt(2)
}

