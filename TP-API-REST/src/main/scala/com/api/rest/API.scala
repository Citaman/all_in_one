package com.api.rest
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.Uri.Path.~
import akka.http.scaladsl.model._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.server.Directives.{parameters, _}
import akka.stream.ActorMaterializer
import Utils.FromMap.to

import scala.io.StdIn
import Utils._
import spray.json.JsValue
object API extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  case class Key(key: Int)
  case class Keys(vec: Vector[Key])
  implicit val keyFormat = jsonFormat1(Key)
  implicit val keysFormat = jsonFormat1(Keys)

  val route =
    pathPrefix("Tips") {
      pathEnd {
        get{
          //TODO il faut que la requete prenenne simplment ceux qui ont fais des Don
          val req = User.get_user_sql()
          val res = req.toString()
          complete(res)
        }
      }~path("give"){
        post {
          entity(as[JsValue]) { json =>
            val user = json.asJsObject.fields("user").convertTo[Int]
            val amount = json.asJsObject.fields("amount").convertTo[Int]
            Gift.post(user,amount)
            complete(json)
          }
        }
       }~path("cancel"){
        delete{
          complete("cancel a gift")
        }
      }~pathPrefix("sum"){
          pathEnd {
            get{
              complete("Sum All users")
            }
          }~path("user"){
            get{
              complete("Sum by User")
            }
          }~path("user"/Segment){ user :String =>
            get{
              complete("cancel the gift of : "+user)
            }
          }
      }
    }~pathPrefix("Sub") {
      pathEnd {
        get {
          complete("All subcribeurs")
        }
      }
    }~pathPrefix("GiveAway") {
      pathEnd {
        get {
          complete("All GiveAway")
        }
      }~path("create"){
        post {
          complete("Create a GiveAway")
        }
      }~path("subs"){
        post {
          complete("Subscirbe to a GiveAway")
        }
      }~path("draw"){
        get {
          complete("Draw for the winner")
        }
      }
    }~pathPrefix("BlackList") {
      pathEnd {
        get {
          complete("All Blacklisted user")
        }
      }~path(Segment){ user :String =>
        get {
          complete("Blacklist : "+user)
        }
      }
    }~pathPrefix("Survey") {
      pathEnd {
        complete("All Survey")
      }~path("create"){
         parameters('name.as[String], 'question.as[String],'choice_1.as[String], 'choice_2.as[String]) { (name, question,choice_1,choice_2) =>
           get {
              complete("Create a Survey : -name = "+name+" -question = "+question+" -1st choice = "+choice_1+" -2nd choice ="+choice_2)
           }
        }
      }~path("participate"){
        post{
          complete("Participate to a Survey")
        }
      }~pathPrefix("result"){
        pathEnd {
          get {
            complete("All Survey Result")
          }
        }~path(Segment){ survey : String =>
          complete("result of the survey : "+survey)
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
/*
         POUR AJOUTER A LA TABLE USER LES DONNE NECESSAIRE
         for( a <- 6 to 100){

           val r = scala.util.Random
            var name = r.alphanumeric.take(10).mkString
           /*var name =""
           for( b <- 0 to 9)
             name += r.nextPrintableChar
           println(name)*/
           println(r.nextBoolean().toString().toUpperCase)
           val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO user(id,name,subscribed,blacklisted) VALUES ('"+a+"','"+name+"',"+r.nextBoolean().toString().toUpperCase+","+r.nextBoolean().toString().toUpperCase+");", Seq("id","name","subscribed","blacklisted"))

         }
*/
/* Pour mettre dans le format Json
req match {
            case Some(r) => val values = r.flatMap(v => to[Key].from(v))
                              complete(values)
            case None => complete("mauvaise table")
          }*/