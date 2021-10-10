package code.service

import play.api.libs.json.Json
import code.datastore.Store
import code.model._
import code.util.SearchUtil._

class OrgzSearch(store: Store) {

  val orgzRelMap = orgzRelatedMap()

  def findXOrgzById(
                    idStr: String
                  ): Option[Xorganization] =
    checkInt(idStr).flatMap { id =>
      orgzRelMap.get(id)
    }

  def findRelOrgzById(
                      idStr: String
                    ): String = {
    findXOrgzById(idStr).map { xorg =>
      Json.prettyPrint(Json.toJson(xorg))
    }.getOrElse(
      s"No organization by id $idStr"
    )
  }

  def findXOrgByName(
                      name: String
                    ): Option[List[Xorganization]] = {
    store.organizationNameMap.get(name).map { lst =>
      lst.map { id => orgzRelMap.get(id) }.flatten
    }
  }

  def findRelOrgByName(
                      name: String
                    ): String = {
    findXOrgByName(name).map { lst =>
      Json.prettyPrint(Json.toJson(lst))
    }.getOrElse(
      s"No organization by name $name"
    )
  }

  def findOrgzById(
                    idStr: String
                  ): Option[Organization] =
    checkInt(idStr).flatMap { id =>
      findOrgzById(id)
    }

  def findOrgzById(id: Int,
                   ids: Array[Int] = store.orgzIds,
                   organizations: List[Organization] = store.organizations
                  ): Option[Organization] = {
    val ind = java.util.Arrays.binarySearch(ids, id)
    if (ind >= 0)
      Some(organizations(ind))
    else
      None
  }

  def orgzRelatedMap(): Map[Int, Xorganization] = {
    (for {
      id <- store.orgzIds
      orgz = findOrgzById(id).get
      users = store.userOrganizationMap.get(id).map { lst =>
        lst.map(_.name)
      }.getOrElse(Nil)
      tickets = store.ticketOrganizationMap.get(id).map { lst =>
        lst.map(_.subject)
      }.getOrElse(Nil)
    } yield (id, Xorganization(orgz, users, tickets))).toMap
  }

}
