package code.util

import code.model._
import play.api.libs.json._

import scala.io.Source
import scala.util.{Failure, Success, Try}


object JsonUtil {

  def usersFrom(fileName: String): List[User] = {

    val tryToReadStringFromFile = Try(Source.fromFile(fileName).getLines().mkString)

    tryToReadStringFromFile map { mayBeJsonString =>
      Json.parse(mayBeJsonString).validate[List[User]] match {
        case s: JsSuccess[List[User]] => {
          Some(s.get)
        }
        case e: JsError => {
          println(s"Error parsing Users : JsError ${e}")
          None
        }
      }
    } match {
      case Success(opt) =>
        opt.get
      case Failure(f) =>
        println(s"Possibly invalid Json for Users : failure ${f}")
        Nil
    }
  }

  def organizationsFrom(fileName: String): List[Organization] = {

    val tryToReadStringFromFile = Try(Source.fromFile(fileName).getLines().mkString)

    tryToReadStringFromFile map { mayBeJsonString =>
      Json.parse(mayBeJsonString).validate[List[Organization]] match {
        case s: JsSuccess[List[Organization]] => {
          Some(s.get)
        }
        case e: JsError => {
          println(s"Error parsing Organizations : JsError ${e}")
          None
        }
      }
    } match {
      case Success(opt) =>
        opt.get
      case Failure(f) =>
        println(s"Possibly invalid Json for Organizations : failure ${f}")
        Nil
    }
  }

  def ticketsFrom(fileName: String): List[Ticket] = {

    val tryToReadStringFromFile = Try(Source.fromFile(fileName).getLines().mkString)

    tryToReadStringFromFile map { mayBeJsonString =>
      Json.parse(mayBeJsonString).validate[List[Ticket]] match {
        case s: JsSuccess[List[Ticket]] => {
          Some(s.get)
        }
        case e: JsError => {
          println(s"Error parsing Tickets : JsError ${e}")
          None
        }
      }
    } match {
      case Success(opt) =>
        opt.get
      case Failure(f) =>
        println(s"Possibly invalid Json for Tickets : failure ${f}")
        Nil
    }
  }

}

