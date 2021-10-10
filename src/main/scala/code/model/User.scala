package code.model

import java.util.Date

import code.util.DateFormatUtil
import play.api.libs.json._

case class User (
             _id : Int,
             url: String,
             external_id: String,
             name: String,
             alias: Option[String],
             created_at: Date,
             active: Boolean,
             verified: Option[Boolean],
             shared: Boolean,
             locale:  Option[String],
             timezone:  Option[String],
             last_login_at: Date,
             email:  Option[String],
             phone:  String,
             signature:  String,
             organization_id: Option[Int],
             tags: List[String],
             suspended: Boolean,
             role: String
           )

object User {
  implicit val dateFormatter: Format[Date] = DateFormatUtil.formatter
  implicit val formatter: OFormat[User] = Json.format[User]
}
