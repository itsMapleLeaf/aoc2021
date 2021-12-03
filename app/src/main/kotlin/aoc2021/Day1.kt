package aoc2021

private fun List<Int>.deltas() = indices.drop(1).map { this[it] - this[it - 1] }

private fun List<Int>.positiveDeltaCount() = deltas().count { it > 0 }

class Day1 : Solution() {
  private val input =
      Resources.file("day1.txt").readLines().map { it.toIntOrNull() }.filterNotNull()
  // private val input = "199,200,208,210,200,207,240,269,260,263".split(",").map { it.toInt() }

  override fun part1(): String {
    return input.positiveDeltaCount().toString()
  }

  override fun part2(): String {
    val threeSums = input.indices.drop(2).map { input[it - 2] + input[it - 1] + input[it] }
    return threeSums.positiveDeltaCount().toString()
  }
}
