package com.ihcl_globalsearch.route

import com.ihcl_globalsearch.model.SpatialSearch
import com.ihcl_globalsearch.service.SpatialSearchService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.spatialSearchRoute() {
    routing {
        post("/Spatial") {
            val latlongdetails = call.receive<SpatialSearch>()
            val pt = latlongdetails.pt
            val d = latlongdetails.d
            val city=latlongdetails.destination.city
            val brandName=latlongdetails.destination.brand_name

            if (city!=null && brandName!=null) {
                val finalHotelRestaurantSpa = SpatialSearchService().spatialserachservice(pt, d, city, brandName)
                call.respond(finalHotelRestaurantSpa)
            }
            else {
                val hotelResortSpaData = SpatialSearchService().spatialserachservice1(pt, d)
                call.respond(hotelResortSpaData)

            }
        }
    }
}