package com.api.rest.Utils

import com.api.rest.SQLite
object GiveAway{
  def get(): Option[Vector[Map[String,Object]]] ={
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM giveaway", Seq("id","name","winner_id"))
    return req
  }
  def post():Unit ={
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO giveaway(id,name,winner_id) VALUES ();", Seq("id","name","winner_id"))

  }
}
class GiveAway(id : Int, name : String, userSubs : List[User], winner : User) {
  def get(): Option[Vector[Map[String,Object]]] ={
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM giveaway", Seq("id","name","winner_id"))
    return req
  }
  def post():Unit ={
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO giveaway(id,name,winner_id) VALUES ();", Seq("id","name","winner_id"))

  }
}
