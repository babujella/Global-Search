package com.ihclglobalsearch.plugin

import com.ihclglobalsearch.route.configureDestinationsRouting
import com.ihclglobalsearch.route.configureSpellCheck
import com.ihclglobalsearch.route.spatialSearchRoute
import configureHotelRestaurantSpaRouting
import io.ktor.server.application.*

fun Application.configureRouting(){
    configureHotelRestaurantSpaRouting()
    configureDestinationsRouting()
    spatialSearchRoute()
    configureSpellCheck()
}