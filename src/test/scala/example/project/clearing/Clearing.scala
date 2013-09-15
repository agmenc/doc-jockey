package example.project.dj.clearing

import example.project.main.ClearingHouseImplicits

trait Clearing extends ClearingHouseImplicits {
  implicit def listOfStringToA[A](listOfString: List[String])(implicit f: String => A): List[A] = listOfString.map(f)
}