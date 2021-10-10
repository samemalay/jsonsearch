package code.service

import play.api.libs.json.Json
import code.datastore.Store
import code.model._
import code.util.SearchUtil._

class UserSearch(store: Store) {

  val orgzService = new OrgzSearch(store)
  val userRelMap = userRelatedMap()

  def findXUserById(
                    idStr: String
                  ): Option[Xuser] =
    checkInt(idStr).flatMap { id =>
      userRelMap.get(id)
    }

  def findRelUserById(
                     idStr: String
                   ): String = {
    findXUserById(idStr).map { xuser =>
      Json.prettyPrint(Json.toJson(xuser))
    }.getOrElse(
      s"No user by id $idStr"
    )
  }

  def findXUserByName(
                    name: String
                  ): Option[List[Xuser]] = {
      store.userNameMap.get(name).map { lst =>
        lst.map { id => userRelMap.get(id) }.flatten
      }
  }

  def findRelUserByName(
                       name: String
                     ): String = {
    findXUserByName(name).map { lst =>
      Json.prettyPrint(Json.toJson(lst))
    }.getOrElse(
      s"No user by name $name"
    )
  }

  def findXUserByTag(
                       tag: String
                     ): Option[List[Xuser]] = {
    store.userTagMap.get(tag).map { lst =>
      lst.map { id => userRelMap.get(id) }.flatten
    }
  }

  def findRelUserByTag(
                       tag: String
                     ): String = {
    findXUserByTag(tag).map { lst =>
      Json.prettyPrint(Json.toJson(lst))
    }.getOrElse(
      s"No user by tag $tag"
    )
  }

  def findUserById(
                    idStr: String
                  ): Option[User] =
    checkInt(idStr).flatMap { id =>
      findUserById(id)
    }

  def findUserById(id: Int,
                   userIds: Array[Int] = store.userIds,
                   users: List[User] = store.users
                  ): Option[User] = {
    val ind = java.util.Arrays.binarySearch(userIds, id)
    if (ind >= 0)
      Some(users(ind))
    else
      None
  }

  def userRelatedMap(): Map[Int, Xuser] = {
    (for {
      id <- store.userIds
      user = findUserById(id).get
      orgz = user.organization_id.map { id =>
        orgzService.findOrgzById(id).map(_.name).getOrElse("")
      }.getOrElse("")
      tickets = store.ticketSubmitterMap.get(id).map { lst =>
        lst.map(_.subject)
      }.getOrElse(Nil)
    } yield (id, Xuser(user, orgz, tickets))).toMap
  }

}
