package com.ihclglobalsearch.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihclglobalsearch.dbconfig.Configuration
import com.ihclglobalsearch.model.FinalHotelRestaurantSpa
import com.ihclglobalsearch.model.Spatial
import com.ihclglobalsearch.model.Root
import com.ihclglobalsearch.model.Doc3
import com.ihclglobalsearch.model.Doc2
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class SpatialSearchService {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    private val hotelList = ArrayList<Any>()
    private val spaList = ArrayList<Any>()
    private val restaurantList = ArrayList<Any>()
    private val destinationList = ArrayList<Any>()

    private val props = Configuration.env
    suspend fun spatialsearchService(pt: String, d: String, destination: String, brandName: String)
    : FinalHotelRestaurantSpa {
        val client = HttpClient(CIO)
        val response: String = client.get(props.spatialSearchURL) {
            url {
                parameters.append("q", "*:*")
                parameters.append("fq", "{!geofilt sfield=location}")
                parameters.append("pt", pt)
                parameters.append("d", d)
                parameters.append("rows", "2000")
                parameters.append("fq", "city:$destination")
                parameters.append("fq", "brand_name:$brandName")

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
                    hotelList.add(s)
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
                    hotelList.add(s)
                    if (dest != null) {
                        destinationList.add(dest)
                    }
                }
            }
        }
        val destinationsList: Set<Any> = LinkedHashSet(destinationList)
        log.info(destinationList.toString())
        return FinalHotelRestaurantSpa(hotelList, spaList, restaurantList, destinationsList)

    }
    suspend fun spatialSearchService1(pt:String, d:String): FinalHotelRestaurantSpa {
        val client = HttpClient(CIO)
        val response: String = client.get(props.spatialSearchURL){
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
                    hotelList.add(hotelname)
                    if (city != null) {
                        destinationList.add(city[0])
                    }
                }
                if (hotelname.contains("Spa") || hotelname.contains("Resort")) {
                    spaList.add(hotelname)
                    restaurantList.add(hotelname)
                    if (city != null) {
                        destinationList.add(city[0])
                    }
                } else {
                    hotelList.add(hotelname)
                    if (city != null) {
                        destinationList.add(city[0])
                    }
                }
            }
        }
        val destination: Set<Any> = LinkedHashSet(destinationList)
        val finalHotelRestaurantSpa1 = FinalHotelRestaurantSpa(hotelList, spaList, restaurantList, destination)
        return finalHotelRestaurantSpa1
    }
}

