package aoc2021.helpers

interface Solution {
  fun part1() = "not implemented"
  fun part2() = "not implemented"

  fun run() {
    println("${this.javaClass.simpleName} Part 1: ${part1()}")
    println("${this.javaClass.simpleName} Part 2: ${part2()}")
  }
}
