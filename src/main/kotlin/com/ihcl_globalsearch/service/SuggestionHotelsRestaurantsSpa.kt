package com.ihcl_globalsearch.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.ihcl_globalsearch.model.FinalHotelRestaurantSpa
import com.ihcl_globalsearch.route.SuggestionTerms
import com.ihcl_globalsearch.route.Suggestions
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Suggestions {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    suspend fun suggestions(param:String): String {
        val client = HttpClient(CIO)
        val response = client.get("http://localhost:8983/solr/Ihcls/suggest?"){
            url{
                parameters.append("suggest","true")
                parameters.append("suggest.build","true")
                parameters.append("suggest.dictionary","suggest")
                parameters.append("suggest.q",param)
            }
        }.body<String>()
        log.info(response)
        return response
    }

    suspend fun extracted(s: String?, input: String): FinalHotelRestaurantSpa? {
        val hoteList = ArrayList<Any>()
        val spaList = ArrayList<Any>()
        val restaurantList=ArrayList<Any>()
        val destinationList=ArrayList<Any>()

        val objectMapper = ObjectMapper()
        val gson = Gson()
        try {
            val jsonNode: JsonNode = objectMapper.readTree(s).findValue(input)
            val string: String = jsonNode.toString()
            val usersuggestions: ArrayList<SuggestionTerms>? =
                gson.fromJson(string, Suggestions::class.java).suggestions
            val resp = SuggestionDestinationService().suggestionDestinations().response.docs

            destinationList.add(resp)
            if (usersuggestions != null) {
                for (item in usersuggestions) {
                    var hotelname = item.term
                    if (hotelname != null) {
                        if (hotelname.contains("Hotel")) {
                            hoteList.add(item)
                        } else if (hotelname.contains("Spa")) {
                            spaList.add(item)
                            restaurantList.add(item)
                        } else {
                            hoteList.add(item)
                        }
                    }
                }
            }
            val destination: Set<Any> = LinkedHashSet(destinationList)
            return FinalHotelRestaurantSpa(hoteList, spaList, restaurantList, destination)
        } catch (e: JsonMappingException) {
            e.printStackTrace()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return null
    }
}

