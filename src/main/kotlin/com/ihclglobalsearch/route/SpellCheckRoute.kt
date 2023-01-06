package com.ihclglobalsearch.route

import com.ihclglobalsearch.service.SpellCheckService
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.uri
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get

fun Application.configureSpellCheck(){

 routing {
     get("Spell/{spell}") {
         val param=call.parameters["spell"]
         println(param)
         val response=SpellCheckService().spellchecker(param!!)
         call.respond(response)
     }
 }
}