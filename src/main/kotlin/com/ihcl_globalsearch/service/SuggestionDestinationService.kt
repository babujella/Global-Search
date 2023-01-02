package com.ihcl_globalsearch.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihcl_globalsearch.model.Destinations
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SuggestionDestinationService {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    suspend fun suggestionDestinations() :Destinations{
        val client = HttpClient(CIO)
        val response: String = client.get("http://localhost:8983/solr/Ihcls/select?fl=city&indent=true&q.op=OR&q=*%3A*&rows=10").body()
        val collectionType = object : TypeToken<Destinations>() {}.type
        val res: Destinations = Gson().fromJson(response, collectionType) as Destinations

        log.info(res.toString())
        return res
    }
}