package code.model

import java.util.{Date, UUID}

import code.util.DateFormatUtil
import play.api.libs.json.{Format, Json, OFormat}

case class Ticket (
                    _id : UUID,
                    url: String,
                    external_id: String,
                    created_at: Date,
                    `type`: Option[String],
                    subject: String,
                    description: Option[String],
                    priority: String,
                    status: String,
                    submitter_id: Int,
                    assignee_id: Option[Int],
                    organization_id: Option[Int],
                    tags: List[String],
                    has_incidents: Boolean,
                    due_at: Option[Date],
                    via: String
                 )

object Ticket {
  implicit val dateFormatter: Format[Date] = DateFormatUtil.formatter
  implicit val formatter: OFormat[Ticket] = Json.format[Ticket]
}
