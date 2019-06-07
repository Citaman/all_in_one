package com.api.rest.Utils

import com.api.rest.SQLite

object Survey {
  def get(): Option[Vector[Map[String,Object]]] ={
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM survey", Seq("id","name","question","choice_1","choice_2"))
    return req
  }
  def post():Unit ={
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO survey(id,name,question,choice_1,choice_2) VALUES ();", Seq("id","name","question","choice_1","choice_2"))

  }
}
class Survey(id : Int , name : String , question : String , Choice1 : String, Choice2: String, Response : List[(User,String)]) {
  def get(): Option[Vector[Map[String,Object]]] ={
    val req = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "SELECT * FROM survey", Seq("id","name","question","choice_1","choice_2"))
    return req
  }
  def post():Unit ={
    val insert = SQLite.request(s"""jdbc:sqlite:/Users/anthonnyolime/test.db""", "INSERT INTO survey(id,name,question,choice_1,choice_2) VALUES ();", Seq("id","name","question","choice_1","choice_2"))

  }
}
