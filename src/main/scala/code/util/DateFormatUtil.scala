package code.util

import java.text.SimpleDateFormat
import java.util.Date

import play.api.libs.json.{Format, JsPath, JsString, Json, Writes}

object DateFormatUtil {
  val FORMAT = "yyyy-MM-dd'T'HH:mm:ss XXX"

  private val dateFmt = new SimpleDateFormat(FORMAT)

  implicit val formatter =
    Format(
      JsPath.read[JsString].map { json =>
        dateFmt.parse(json.value)
      },

      Writes[Date] { dt =>
        Json.toJson(dateFmt.format(dt))
      }
    )
}