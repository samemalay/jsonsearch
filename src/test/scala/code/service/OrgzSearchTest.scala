package code.service

import code.datastore._
import code.model._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should._

class OrgzSearchTest extends AnyFlatSpec with Matchers {

  val inputDir = "src/test/resources/data"
  val store = new Store(inputDir)
  val service = new OrgzSearch(store)

  val xorg = Xorganization(
    store.organizations(0),
    List("Francisca Rasmussen", "Ingrid Wagner"),
    List("A Catastrophe in Korea (North)", "A Catastrophe in Hungary")
  )

  "OrgzSearch" should "find proper organization for given id" in {
      val expected = Some(xorg)
      val found = service.findXOrgzById("101")
      found should be(expected)
  }

  it should "find proper organizations for given name" in {
    val expected = Some(List(xorg))
    val found = service.findXOrgByName("Enthaze")
    found should be(expected)
  }

}
