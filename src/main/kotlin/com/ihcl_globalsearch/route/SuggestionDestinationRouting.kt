package com.ihcl_globalsearch.route

import com.ihcl_globalsearch.service.SuggestionDestinationService
import configureHotelRestaurantSpaRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.Configuremodules(){
    configureHotelRestaurantSpaRouting()
    configureDestinationsRouting()
    spatialSearchRoute()
}
fun Application.configureDestinationsRouting(){
    routing {
        get("/destinations") {
            val destinations=SuggestionDestinationService().suggestionDestinations().response.docs
           call.respond(destinations)
        }
    }
}