package com.ihcl_globalsearch.route

import com.ihcl_globalsearch.service.SuggestionDestinationService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureDestinationsRouting(){
    routing {
        get("/destinations") {
            val resp=SuggestionDestinationService().suggestionDestinations().response.docs
           call.respond(resp)
        }
    }
}