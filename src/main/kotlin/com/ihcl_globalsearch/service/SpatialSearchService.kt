package com.ihcl_globalsearch.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihcl_globalsearch.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class SpatialSearchService {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    val hoteList = ArrayList<Any>()
    val spaList = ArrayList<Any>()
    val restaurantList = ArrayList<Any>()
    val destinationList = ArrayList<Any>()
    suspend fun spatialserachservice(pt: String, d: String, destination: String, brand_name: String): FinalHotelRestaurantSpa {
        val client = HttpClient(CIO)
        val response: String = client.get("http://localhost:8983/solr/Ihcls/select?&") {
            url {
                parameters.append("q", "*:*")
                parameters.append("fq", "{!geofilt sfield=location}")
                parameters.append("pt", pt)
                parameters.append("d", d)
                parameters.append("rows", "2000")
                parameters.append("fq", "city:$destination")
                parameters.append("fq", "brand_name:$brand_name")

            }
        }.body()
        val collectionType = object : TypeToken<Spatial>() {}.type
        val finalData: ArrayList<Doc2>? = (Gson().fromJson(response, collectionType) as Spatial).response?.docs
        log.info(finalData.toString())

        for (item in finalData!!) {
            val s = item.hotel_name?.get(0).toString()
            val dest = item.city
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
        log.info(destinationList.toString())
        return FinalHotelRestaurantSpa(hoteList, spaList, restaurantList, destinationsList)

    }
    suspend fun spatialserachservice1(pt:String,d:String): FinalHotelRestaurantSpa {
        val client = HttpClient(CIO)
        val response: String = client.get("http://localhost:8983/solr/Ihcls/select?&"){
            url{
                parameters.append("q", "*:*")
                parameters.append("fq", "{!geofilt sfield=location}")
                parameters.append("pt", pt)
                parameters.append("d", d)
                parameters.append("rows", "2000")
            }
        }.body()
        val collectionType = object : TypeToken<Root>() {}.type
        val latlongdata: ArrayList<Doc3>? = (Gson().fromJson(response, collectionType) as Root).response?.docs
        log.info(latlongdata.toString())
        for (item in latlongdata!!) {
            val hotelname = item.hotel_name?.get(0).toString()
            val city = item.city
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
        val destination: Set<Any> = LinkedHashSet(destinationList)
        var finalHotelRestaurantSpa1 = FinalHotelRestaurantSpa(hoteList, spaList, restaurantList, destination)
        return finalHotelRestaurantSpa1
    }
}

