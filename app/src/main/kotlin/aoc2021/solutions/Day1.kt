package aoc2021.solutions

import aoc2021.helpers.Resources
import aoc2021.helpers.Solution
import aoc2021.helpers.nonEmptyLines


class Day1 : Solution {
    private val input = Resources.file("day1.txt").nonEmptyLines().map { it.toInt() }

    // private val input = "199,200,208,210,200,207,240,269,260,263".split(",").map { it.toInt() }

    override fun part1(): String {
        return input.positiveDeltaCount().toString()
    }

    override fun part2(): String {
        val threeSums = input.indices.drop(2).map { input[it - 2] + input[it - 1] + input[it] }
        return threeSums.positiveDeltaCount().toString()
    }

    private fun List<Int>.deltas() = indices.drop(1).map { this[it] - this[it - 1] }

    private fun List<Int>.positiveDeltaCount() = deltas().count { it > 0 }
}
