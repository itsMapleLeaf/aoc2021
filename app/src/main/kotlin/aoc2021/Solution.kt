package aoc2021

abstract class Solution {
  protected open fun part1() = "not implemented"
  protected open fun part2() = "not implemented"

  fun run() {
    println("${this.javaClass.simpleName} Part 1: ${part1()}")
    println("${this.javaClass.simpleName} Part 2: ${part2()}")
  }
}
