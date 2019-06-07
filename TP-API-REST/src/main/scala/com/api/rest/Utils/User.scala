package com.api.rest.Utils

import com.api.rest.SQLite

object User{
  def get_user_sql(): Option[Vector[Map[String,Object]]]={
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM user", Seq("id","name","subscribed","blacklisted"))
    return req
  }
  def post_user_sql():Unit ={
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO user(id,name,subscribed,blacklisted) VALUES ();", Seq("id","name","subscribed","blacklisted"))

  }
}

class User(id : Int, name : String, subscribe: Boolean,Blacklisted : Boolean) {
  def get_user_sql():Option[Vector[Map[String,Object]]] ={
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM user", Seq("id","name","subscribed","blacklisted"))
    return req
  }
  def post_user_sql():Unit ={
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO user(id,name,subscribed,blacklisted) VALUES ();", Seq("id","name","subscribed","blacklisted"))

  }
}
