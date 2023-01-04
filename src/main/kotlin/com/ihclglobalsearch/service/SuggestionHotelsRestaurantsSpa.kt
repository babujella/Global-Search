package com.ihclglobalsearch.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.ihclglobalsearch.dbconfig.Configuration
import com.ihclglobalsearch.model.FinalHotelRestaurantSpa
import com.ihclglobalsearch.route.SuggestionTerms
import com.ihclglobalsearch.route.Suggestions
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SuggestionHotelsRestaurantsSpa {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    private val props = Configuration.env
    suspend fun suggestions(param:String): String {
        val client = HttpClient(CIO)
        val response = client.get(props.suggestionsURL){
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
                    val hotelname = item.term
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
            log.error("JsonMappingException", e)
        } catch (e: JsonProcessingException) {
            log.error("JsonProcessingException", e)
        }
        return null
    }
}

