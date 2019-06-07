package com.api.rest.Utils

import com.api.rest.SQLite

object  Gift {

  def get(): Option[Vector[Map[String,Object]]] = {
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM gift", Seq("id","user","amount"))
    return req
  }

  def post(user:Int , amount :Int):Unit = {
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO gift(user,amount) VALUES ("+user+","+amount+");", Seq("id", "user", "amount"))
  }

}

class Gift(id: Int , user :User,amout : Int) {

  def get() : Option[Vector[Map[String,Object]]] = {
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM gift", Seq("id","user","amount"))
    return req
  }

  def post(user:Int , amount :Int):Unit = {
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO gift(id,user,amount) VALUES ();", Seq("id","user","amount"))
  }

}
