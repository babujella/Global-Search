package com.ihclglobalsearch.route

import com.ihclglobalsearch.dataimport.kodein
import com.ihclglobalsearch.model.SpatialSearch
import com.ihclglobalsearch.service.SpatialSearchService
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.post
import org.kodein.di.instance

fun Application.spatialSearchRoute() {

    val spatialSearchService by kodein.instance<SpatialSearchService>()

    routing {
        post("/Spatial") {
            val latlongdetails = call.receive<SpatialSearch>()
            val pt = latlongdetails.pt
            val d = latlongdetails.d
            val city=latlongdetails.destination.city
            val brandName=latlongdetails.destination.brand_name

            if (city!=null && brandName!=null) {
                val finalHotelRestaurantSpa = spatialSearchService.spatialsearchService(pt, d, city, brandName)
                call.respond(finalHotelRestaurantSpa)
            }
            else {
                val hotelResortSpaData = spatialSearchService.spatialSearchService1(pt, d)
                call.respond(hotelResortSpaData)

            }
        }
    }
}
