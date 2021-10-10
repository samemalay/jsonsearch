package code.model

import play.api.libs.json.{Json, OFormat}

case class Xorganization(
                          organization: Organization,
                          users: List[String],
                          tickets: List[String]
                        )

object Xorganization {
  implicit val formatter: OFormat[Xorganization] = Json.format[Xorganization]
}