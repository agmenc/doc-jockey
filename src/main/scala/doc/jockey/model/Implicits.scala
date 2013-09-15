package doc.jockey.model

object Implicits {
  implicit def onOrOff(bool: Boolean): String = if (bool) "on" else "off"
}