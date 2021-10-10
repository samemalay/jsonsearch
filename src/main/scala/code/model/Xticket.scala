package code.model

import play.api.libs.json.{Format, Json, OFormat}

case class Xticket(
                    ticket: Ticket,
                    organization: String,
                    user: String
                  )

object Xticket {
  implicit val formatter: OFormat[Xticket] = Json.format[Xticket]
}