package com.ihcl_globalsearch.route

import com.ihcl_globalsearch.model.FinalHotelRestaurantSpa
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
            var pt = latlongdetails.pt
            var d = latlongdetails.d
            var city=latlongdetails.destination.city
            var brandName=latlongdetails.destination.brand_name

            if (city!=null && brandName!=null) {

                var finaldata = SpatialSearchService().spatialserachservice(pt, d, city, brandName).response?.docs

                var hoteList = ArrayList<Any>()
                var spaList = ArrayList<Any>()
                var restaurantList = ArrayList<Any>()
                var destinationList = ArrayList<Any>()

                for (item in finaldata!!) {
                    var s = item.hotel_name?.get(0).toString()
                    var dest = item.city
                    if (s != null) {
                        if (s.contains("Hotel")) {
                            hoteList.add(s)
                            if (dest != null) {
                                destinationList.add(dest)
                            }
                        }
                        if (s.contains("Spa") || s.contains("Resort")) {
                            spaList.add(s)
                            restaurantList.add(s)
                            if (dest != null) {
                                destinationList.add(dest)
                            }
                        } else {
                            hoteList.add(s)
                            if (dest != null) {
                                destinationList.add(dest)
                            }
                        }
                    }
                }
                val destinationsList: Set<Any> = LinkedHashSet(destinationList)
                var finalHotelRestaurantSpa = FinalHotelRestaurantSpa(hoteList, spaList, restaurantList, destinationsList)
                call.respond(finalHotelRestaurantSpa)
            }
            else {
                var hoteList = ArrayList<Any>()
                var spaList = ArrayList<Any>()
                var restaurantList = ArrayList<Any>()
                var destinationList = ArrayList<Any>()

                val latlongdata = SpatialSearchService().spatialserachservice1(pt, d).response?.docs
                for (item in latlongdata!!) {
                    var hotelname = item.hotel_name?.get(0).toString()
                    var city = item.city
                    if (hotelname != null) {
                        if (hotelname.contains("Hotel")) {
                            hoteList.add(hotelname)
                            if (city != null) {
                                destinationList.add(city)
                            }
                        }
                        if (hotelname.contains("Spa") || hotelname.contains("Resort")) {
                            spaList.add(hotelname)
                            restaurantList.add(hotelname)
                            if (city != null) {
                                destinationList.add(city)
                            }
                        } else {
                            hoteList.add(hotelname)
                            if (city != null) {
                                destinationList.add(city)
                            }
                        }
                    }
                }
                val s1: Set<Any> = LinkedHashSet(destinationList)
                var finalHotelRestaurantSpa1 = FinalHotelRestaurantSpa(hoteList, spaList, restaurantList, s1)
                call.respond(finalHotelRestaurantSpa1)

            }
        }
    }
}