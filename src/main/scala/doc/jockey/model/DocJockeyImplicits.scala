package doc.jockey.model

trait DocJockeyImplicits {
  implicit def listToStrings[T](original: List[T])(implicit f: T => String): String = original.map(f).mkString(", ")
  implicit def listOfThingsToListOfStrings[T](original: List[T])(implicit f: T => String): List[String] = original.map(f)
}

trait OnOrOff {
  implicit def onOrOff(bool: Boolean): String = if (bool) "on" else "off"
}

trait TickOrDash {
  implicit def onOrOff(bool: Boolean): String = if (bool) "✓" else "-"
}

trait YesOrNo {
  implicit def onOrOff(bool: Boolean): String = if (bool) "Yes" else "No"
}