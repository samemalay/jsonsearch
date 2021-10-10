package code.model

import play.api.libs.json.{Json, OFormat}

case class Xuser(
                  user: User,
                  organization: String,
                  tickets: List[String]
                )

object Xuser {
  implicit val formatter: OFormat[Xuser] = Json.format[Xuser]
}