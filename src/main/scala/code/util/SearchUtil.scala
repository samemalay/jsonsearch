package code.util

import scala.util.{Failure, Success, Try}

import java.util.UUID

object SearchUtil {

  def checkInt(idStr: String): Option[Int] = Try {
    Integer.parseInt(idStr.trim)
  } match {
    case Success(i) => Some(i)
    case Failure(ex) =>
      println(s"$idStr is not a valid int value ${ex.getMessage}")
      None
  }

  def checkUUID(idStr: String): Option[UUID] =
    Try {
      UUID.fromString(idStr)
    } match {
      case Success(i) => Some(i)
      case Failure(ex) =>
        println(s"$idStr is not a valid UUID value ${ex.getMessage}")
        None
    }

}
