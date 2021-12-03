package aoc2021.helpers

import java.io.File
import kotlin.io.path.Path

object Resources {
  private val resourceFolderPath = Path("src/main/resources")

  fun file(fileName: String): File = resourceFolderPath.resolve(fileName).toFile()
}
