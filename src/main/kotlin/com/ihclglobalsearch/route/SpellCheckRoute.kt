package com.ihclglobalsearch.route

import com.ihclglobalsearch.dataimport.kodein
import com.ihclglobalsearch.service.SpellCheckService
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.uri
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import org.kodein.di.instance

fun Application.configureSpellCheck(){

    val spellCheckService by kodein.instance<SpellCheckService>()

 routing {
     get("/Spellcheck/{spellCheck}") {
         val params=call.request.uri
         val response=spellCheckService.spellchecker(params)
         call.respond(response)
     }
 }
}