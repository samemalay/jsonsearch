package code.service

import java.util.UUID
import play.api.libs.json.Json
import code.datastore.Store
import code.model._
import code.util.SearchUtil._

class TicketSearch(store: Store) {

  val orgzService = new OrgzSearch(store)
  val userService = new UserSearch(store)
  val ticketRelMap = ticketRelatedMap()

  def findXTicketById(
                      idStr: String
                    ): Option[Xticket] =
    checkUUID(idStr).flatMap { id =>
      ticketRelMap.get(id)
    }

  def findRelticketById(
                       idStr: String
                     ): String = {
    findXTicketById(idStr).map { xtkt =>
      Json.prettyPrint(Json.toJson(xtkt))
    }.getOrElse(
      s"No ticket by id $idStr"
    )
  }

  def findXTicketBySub(
                       subject: String
                    ): Option[List[Xticket]] =
    store.ticketSubjectMap.get(subject).map { lst =>
      lst.map { id => ticketRelMap.get(id) }.flatten
    }

  def findRelTicketBySub(
                        subject: String
                      ): String = {
    findXTicketBySub(subject).map { lst =>
      Json.prettyPrint(Json.toJson(lst))
    }.getOrElse(
      s"No ticket by subject $subject"
    )
  }

  def findXticketByDesc(
                         desc: String
                       ): Option[List[Xticket]] = {
    val opt = if (desc.isEmpty) None else Some(desc)
    store.ticketOptDescMap.get(opt).map { lst =>
      lst.map { id => ticketRelMap.get(id) }.flatten
    }
  }

  def findRelticketByDesc(
                         desc: String
                       ): String = {
    findXticketByDesc(desc).map { lst =>
      Json.prettyPrint(Json.toJson(lst))
    }.getOrElse(
      s"No ticket by description $desc"
    )
  }

  def findTicketById(idStr: String,
                     uuidMap: Map[UUID, Ticket] = store.ticketUuidMap
                    ): Option[Ticket] =
    checkUUID(idStr).flatMap { id =>
      uuidMap.get(id)
    }

  def ticketRelatedMap(): Map[UUID, Xticket] = {
    (for {
      (k, v) <- store.ticketUuidMap
      orgz = v.organization_id.map { id =>
        orgzService.findOrgzById(id).map(_.name).getOrElse("")
      }.getOrElse("")
      user = userService.findUserById(v.submitter_id).map(_.name).getOrElse("")
    } yield (k, Xticket(v, orgz, user))).toMap
  }

}
