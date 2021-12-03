package aoc2021.helpers

import java.io.File

fun File.nonEmptyLines() = readLines().filter { it.isNotEmpty() }
