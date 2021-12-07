package aoc2021.helpers

import java.io.File

fun File.readNonEmptyLines() = readLines().filter { it.isNotEmpty() }
