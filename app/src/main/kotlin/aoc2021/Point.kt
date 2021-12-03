package aoc2021

data class Point(val x: Int = 0, val y: Int = x) {
  operator fun plus(other: Point) = Point(x + other.x, y + other.y)

  operator fun minus(other: Point) = Point(x - other.x, y - other.y)

  operator fun times(other: Point) = Point(x * other.x, y * other.y)
  operator fun times(other: Int) = Point(x * other, y * other)

  operator fun div(other: Point) = Point(x / other.x, y / other.y)
  operator fun div(other: Int) = Point(x / other, y / other)

  override fun toString() = "($x, $y)"

  fun componentProduct() = x * y
}
