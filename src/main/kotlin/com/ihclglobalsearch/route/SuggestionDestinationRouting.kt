package com.ihclglobalsearch.route

import com.ihclglobalsearch.dataimport.kodein
import com.ihclglobalsearch.service.SuggestionDestinationService
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import org.kodein.di.instance


fun Application.configureDestinationsRouting(){
    val suggestionDestinationService by kodein.instance<SuggestionDestinationService>()
    routing {
        get("/destinations") {
            val destinations=suggestionDestinationService.suggestionDestinations().response.docs
           call.respond(destinations)
        }
    }
}
