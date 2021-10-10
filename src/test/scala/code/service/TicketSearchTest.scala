package code.service

import code.datastore._
import code.model._
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should._

class TicketSearchTest extends AnyFlatSpec with Matchers {

  val inputDir = "src/test/resources/data"
  val store = new Store(inputDir)
  val service = new TicketSearch(store)

  val xticket = Xticket(
    store.tickets(0),
    "Enthaze",
    "Francisca Rasmussen"
  )

  "TicketSearch" should "find proper ticket for a given id" in {
      val expected = Some(xticket)
      val found = service.findXTicketById("436bf9b0-1147-4c0a-8439-6f79833bff5b")
      found should be(expected)
  }

  it should "find proper tickets for given subject" in {
    val expected = Some(List(xticket))
    val found = service.findXTicketBySub("A Catastrophe in Korea (North)")
    found should be(expected)
  }

  it should "find proper tickets for given description" in {
    val expected = Some(List(xticket))
    val found = service.findXticketByDesc("Nostrud ad  laboris nostrud velit ipsum.")
    found should be(expected)
  }

  /*
  it should "find proper ticket for given id" in {
    val idStr = "436bf9b0-1147-4c0a-8439-6f79833bff5b"
    val expected = Some(store.tickets(0))
    val found = service.findTicketById(idStr, store.ticketUuidMap)
    println("---------------------------")
    val xticket = store.ticketRelMap.get(UUID.fromString(idStr))
    println(Json.prettyPrint(Json.toJson(xticket)))
    println("---------------------------")
    found should be(expected)
  }

  it should "find proper tickets for given optional description" in {
    val expected1 = Some(List(store.tickets(0)))
    val expected2 = Some(List(store.tickets(4)))
    val found1 = service.findTicketByDesc(Some("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."),
      store.ticketOptDescMap)
    val found2 = service.findTicketByDesc(None, store.ticketOptDescMap)
    found1 should be(expected1)
    found2 should be(expected2)
  }
   */
  
}
