package code.model

import java.util.Date

import code.util.DateFormatUtil
import play.api.libs.json.{Format, Json, OFormat}

case class Organization (
                          _id : Int,
                          url: String,
                          external_id: String,
                          name: String,
                          domain_names: List[String],
                          created_at: Date,
                          details: String,
                          shared_tickets: Boolean,
                          tags: List[String]
                       )

object Organization {
  implicit val dateFormatter: Format[Date] = DateFormatUtil.formatter
  implicit val formatter: OFormat[Organization] = Json.format[Organization]
}
