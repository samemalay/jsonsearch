package code.service

import code.datastore._
import code.model._
import org.scalatest._
import matchers.should._
import org.scalatest.flatspec.AnyFlatSpec

class UserSearchTest extends AnyFlatSpec with Matchers {

  val inputDir = "src/test/resources/data"
  val store = new Store(inputDir)
  val service = new UserSearch(store)

  val xuser = Xuser(
    store.users(0),
    "Enthaze",
    List("A Catastrophe in Korea (North)", "A Catastrophe in Hungary")
  )

  "UserSearch" should "find proper user for given id" in {
      val expected = Some(xuser)
      val found = service.findXUserById("1")
      found should be(expected)
  }

  it should "find proper users for given name" in {
    val expected = Some(List(xuser))
    val found = service.findXUserByName("Francisca Rasmussen")
    found should be(expected)
  }

  it should "find proper users for given tag" in {
    val expected = Some(List(xuser))
    val found = service.findXUserByTag("Sutton")
    found should be(expected)
  }

}
