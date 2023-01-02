package com.ihcl_globalsearch.route

import com.ihcl_globalsearch.model.Filters
import com.ihcl_globalsearch.service.FiltersService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureFilters(){
    routing {
        get ("/Hello"){
           val filtersdetails=call.receive<Filters>()
            val brand_name=filtersdetails.brand_name
           val pms_name=filtersdetails.pms_name
           val hotel_name=filtersdetails.hotel_name
            println("$brand_name and $hotel_name")

            val filtersMethod=FiltersService().filterservice(brand_name,hotel_name,pms_name)
            var getFilters=filtersMethod.response.docs
            println(getFilters)
            call.respond(getFilters)
        }
    }
}