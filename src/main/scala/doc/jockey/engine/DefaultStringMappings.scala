package doc.jockey.engine

object DefaultStringMappings {
  lazy val mappings = Map(
    "Yes" -> true,
    "No" -> false,
    "-" -> false,
    "&123;" -> true, // tick
    "&124;" -> false // cross
  )
}