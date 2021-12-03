package aoc2021

import java.io.File

fun File.nonEmptyLines() = readLines().filter { it.isNotEmpty() }
