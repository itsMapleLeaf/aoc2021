package aoc2021

// private val input = """forward 5
// down 5
// forward 8
// up 3
// down 8
// forward 2""".split("\n")

private val input = Resources.file("day2.txt").nonEmptyLines()

private fun String.firstLetterUpperCase(): String = first().uppercase() + drop(1)

data class SubmarineCommand(val direction: SubmarineDirection, val value: Int) {
  companion object {
    fun fromString(s: String): SubmarineCommand {
      val (type, value) = s.split(" ")
      return SubmarineCommand(
          SubmarineDirection.valueOf(type.firstLetterUpperCase()),
          value.toInt()
      )
    }
  }
}

enum class SubmarineDirection {
  Down,
  Up,
  Forward,
}

data class BasicSubmarine(val position: Point = Point(0, 0)) {
  fun move(delta: Point) = copy(position = position + delta)

  fun apply(command: SubmarineCommand) =
      when (command.direction) {
        SubmarineDirection.Down -> move(Point(0, command.value))
        SubmarineDirection.Up -> move(Point(0, -command.value))
        SubmarineDirection.Forward -> move(Point(command.value, 0))
      }
}

data class ComplicatedSubmarine(val position: Point = Point(0, 0), val aim: Int = 0) {
  fun tilt(delta: Int) = copy(aim = aim + delta)

  fun move(distance: Int) = copy(position = position + Point(distance, aim * distance))

  fun apply(command: SubmarineCommand) =
      when (command.direction) {
        SubmarineDirection.Down -> tilt(command.value)
        SubmarineDirection.Up -> tilt(-command.value)
        SubmarineDirection.Forward -> move(command.value)
      }
}

class Day2 : Solution() {
  override fun part1(): String {
    val sub = input.map(SubmarineCommand::fromString).fold(BasicSubmarine(), BasicSubmarine::apply)
    return "${sub.position.componentProduct()}"
  }

  override fun part2(): String {
    val sub =
        input
            .map(SubmarineCommand::fromString)
            .fold(ComplicatedSubmarine(), ComplicatedSubmarine::apply)

    return "${sub.position.componentProduct()}"
  }
}
