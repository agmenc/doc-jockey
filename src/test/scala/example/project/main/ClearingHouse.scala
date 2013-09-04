package example.project.main

trait ClearingHouse

trait ClearingHouseImplicits {
  implicit def convert(name: String): ClearingHouse = name match {
    case "LCH-FCM" => LchFcm
    case "LCH-SCM" => LchScm
  }
}

case object LchScm extends ClearingHouse
case object LchFcm extends ClearingHouse