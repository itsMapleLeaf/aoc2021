package aoc2021

import kotlin.io.path.Path

object Resources {
  private val resourceFolderPath = Path("src/main/resources")

  public fun file(fileName: String) = resourceFolderPath.resolve(fileName).toFile()
}
