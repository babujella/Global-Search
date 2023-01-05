package com.ihclglobalsearch.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihclglobalsearch.dbconfig.Configuration
import com.ihclglobalsearch.model.Destinations
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SuggestionDestinationService {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    val prop=Configuration.env
    suspend fun suggestionDestinations() :Destinations{
        val client = HttpClient(CIO)
        val response: String = client.get("${prop.destinationURL}").body()
        val collectionType = object : TypeToken<Destinations>() {}.type
        val res: Destinations = Gson().fromJson(response, collectionType) as Destinations

        log.info(res.toString())
        return res
    }
}