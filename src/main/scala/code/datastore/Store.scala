package code.datastore

import java.util.UUID

import code.model._
import code.util.JsonUtil

import scala.collection.mutable.{Map => MMap}

class Store(inputDir: String) {

  val (users, organizations, tickets) = readData(inputDir)
  val userIds = users.map(_._id).toArray
  val userNameMap = userNmMap()
  val userTagMap = userTgMap()
  val ticketUuidMap = ticketUMap()
  val ticketOptDescMap = ticketOptDescrMap()
  val ticketSubmitterMap = ticketSubmtMap()
  val ticketOrganizationMap = ticketOrgzMap()
  val ticketSubjectMap = ticketSubMap()
  val userOrganizationMap = userOrgzMap()
  val orgzIds = organizations.map(_._id).toArray
  val organizationNameMap = orgzNmMap()

  def userNmMap(): Map[String, List[Int]] = {
    val mMap = MMap[String, List[Int]]()
    users.map{ u =>
      (u.name, u._id)
    }.foreach { t =>
      val newLst = mMap.get(t._1).map{ lst =>
        lst :+ t._2
      }.getOrElse {
        List(t._2)
      }
      mMap.put(t._1, newLst)
    }
    mMap.toMap
  }

  def userOrgzMap(): Map[Int, List[User]] = {
    val mMap = MMap[Int, List[User]]()
    users.map{ u =>
      (u.organization_id, u)
    }.filter(_._1.nonEmpty).foreach { t =>
      val newLst = mMap.get(t._1.get).map{ lst =>
        lst :+ t._2
      }.getOrElse {
        List(t._2)
      }
      mMap.put(t._1.get, newLst)
    }
    mMap.toMap
  }

  def userTgMap(): Map[String, List[Int]] = {
    val mMap = MMap[String, List[Int]]()
    users.map{ u =>
      (u.tags, u._id)
    }.foreach { t =>
      t._1.foreach { tag =>
        val newLst = mMap.get(tag).map { lst =>
          lst :+ t._2
        }.getOrElse {
          List(t._2)
        }
        mMap.put(tag, newLst)
      }
    }
    mMap.toMap
  }

  def ticketUMap(): Map[UUID, Ticket] =
    tickets.map{ u =>
      (u._id, u)
    }.toMap

  def ticketSubMap(): Map[String, List[UUID]] = {
    val mMap = MMap[String, List[UUID]]()
    tickets.map{ tkt =>
      (tkt.subject, tkt._id)
    }.foreach { t =>
      val newLst = mMap.get(t._1).map{ lst =>
        lst :+ t._2
      }.getOrElse {
        List(t._2)
      }
      mMap.put(t._1, newLst)
    }
    mMap.toMap
  }

  def ticketOptDescrMap(): Map[Option[String], List[UUID]] = {
    val mMap = MMap[Option[String], List[UUID]]()
    tickets.map{ tkt =>
      (tkt.description, tkt._id)
    }.foreach { t =>
      val newLst = mMap.get(t._1).map{ lst =>
        lst :+ t._2
      }.getOrElse {
        List(t._2)
      }
      mMap.put(t._1, newLst)
    }
    mMap.toMap
  }

  def ticketSubmtMap(): Map[Int, List[Ticket]] = {
    val mMap = MMap[Int, List[Ticket]]()
    tickets.map{ tkt =>
      (tkt.submitter_id, tkt)
    }.foreach { t =>
      val newLst = mMap.get(t._1).map{ lst =>
        lst :+ t._2
      }.getOrElse {
        List(t._2)
      }
      mMap.put(t._1, newLst)
    }
    mMap.toMap
  }

  def ticketOrgzMap(): Map[Int, List[Ticket]] = {
    val mMap = MMap[Int, List[Ticket]]()
    tickets.map{ tkt =>
      (tkt.organization_id, tkt)
    }.filter(_._1.nonEmpty).foreach { t =>
      val newLst = mMap.get(t._1.get).map{ lst =>
        lst :+ t._2
      }.getOrElse {
        List(t._2)
      }
      mMap.put(t._1.get, newLst)
    }
    mMap.toMap
  }

  def orgzNmMap(): Map[String, List[Int]] = {
    val mMap = MMap[String, List[Int]]()
    organizations.map{ o =>
      (o.name, o._id)
    }.foreach { t =>
      val newLst = mMap.get(t._1).map{ lst =>
        lst :+ t._2
      }.getOrElse {
        List(t._2)
      }
      mMap.put(t._1, newLst)
    }
    mMap.toMap
  }

  def readData(inputDir: String): (List[User], List[Organization], List[Ticket]) = {
    val userFile = inputDir + "/users.json"
    val organizationFile = inputDir + "/organizations.json"
    val ticketFile = inputDir + "/tickets.json"
    (
      JsonUtil.usersFrom(userFile),
      JsonUtil.organizationsFrom(organizationFile),
      JsonUtil.ticketsFrom(ticketFile)
    )
  }

}
