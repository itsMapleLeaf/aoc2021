package aoc2021

import aoc2021.helpers.Solution
import aoc2021.solutions.Day1
import aoc2021.solutions.Day2
import aoc2021.solutions.Day3
import aoc2021.solutions.Day4

val solutions = listOf(
    Day1,
    Day2,
    Day3,
    Day4,
)

fun main() {
    solutions.forEach(Solution::run)
}
