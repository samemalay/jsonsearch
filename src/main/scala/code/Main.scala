package code

import scala.io.StdIn._

import code.datastore.Store
import code.service._

object Main extends App {

  val inputDir = args(0)
  val store = new Store(inputDir)
  val userService = new UserSearch(store)
  val orgzService = new OrgzSearch(store)
  val ticketService = new TicketSearch(store)

  var inp1 = "0"
  var inp2 = "0"
  var inp3 = "0"
  var inp4 = "0"
  while (inp1 != "1" && inp1 != "2" && inp1 != "q") {
    println("Enter \n 1 to Search \n 2 to View list of searchable fields \n q to quit\n")
    inp1 = readLine()
    inp1 match {
      case "1" =>
        while (inp2 != "1" && inp2 != "2" && inp2 != "3" && inp2 != "q") {
          println("Select 1) User 2) Organization 3) Ticket q) quit : ")
          inp2 = readLine()

          inp2 match {
            case "q" =>
              inp1 = "q"

            case "1" =>
              while (inp3 != "1" && inp3 != "2" && inp3 != "3" && inp3 != "q") {
                println("Select search field - 1) _id 2) name 3) tag q) quit : ")
                inp3 = readLine()
              }
              if (inp3 == "q")
                inp2 = "q"
              else {
                println("Search value : ")
                inp4 = readLine()
                //User
                inp3 match {
                  case "1" =>
                    println(userService.findRelUserById(inp4))
                  case "2" =>
                    println(userService.findRelUserByName(inp4))
                  case "3" =>
                    println(userService.findRelUserByTag(inp4))
                  case _ =>
                    ;
                }
                inp2 = "0"
              }

            case "2" =>
              while (inp3 != "1" && inp3 != "2" && inp3 != "q") {
                println("Select 1) _id 2) name q) quit : ")
                inp3 = readLine()
              }
              println("Search value : ")
              inp4 = readLine()
              if (inp3 == "q")
                inp2 = "q"
              else {
                println("Search value : ")
                inp4 = readLine()
                //Organization
                inp3 match {
                  case "1" =>
                    println(orgzService.findRelOrgzById(inp4))
                  case "2" =>
                    println(orgzService.findRelOrgByName(inp4))
                  case _ =>
                    ;
                }
                inp2 = "0"
              }

            case "3" =>
              while (inp3 != "1" && inp3 != "2" && inp3 != "3" && inp3 != "q") {
                println("Select search field - 1) _id 2) subject 3) description q) quit : ")
                inp3 = readLine()
              }
              if (inp3 == "q")
                inp2 = "q"
              else {
                println("Search value : ")
                inp4 = readLine()
                //Ticket
                inp3 match {
                  case "1" =>
                    println(ticketService.findRelticketById(inp4))
                  case "2" =>
                    println(ticketService.findRelTicketBySub(inp4))
                  case "3" =>
                    println(ticketService.findRelticketByDesc(inp4))
                  case _ =>
                    ;
                }
                inp2 = "0"
              }

            case _ =>
              ;
          }
        }
      case "2" =>
        println("Search fields for ")
        println("Users : _id, name, tags")
        println("Tickets : _id, subject, description, tags")
        println("Organizations : _id, name, tags\n")
        inp1 = "0"
      case "q" =>
        ;
    }
  }


}
