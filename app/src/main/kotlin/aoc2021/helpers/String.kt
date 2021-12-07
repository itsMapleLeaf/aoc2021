package aoc2021.helpers

fun String.nonEmptyLines() = lines().filter { it.isNotEmpty() }