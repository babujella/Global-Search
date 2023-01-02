package com.ihcl_globalsearch.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.ihcl_globalsearch.route.Suggestions
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

class Suggestions {
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
        return response
    }
}
class SuggestedUserTerms {
    fun extracted(s: String?, input: String): Suggestions? {
        val objectMapper = ObjectMapper()
        val gson = Gson()
        try {
            val j: JsonNode = objectMapper.readTree(s).findValue(input)
            val s1: String = j.toString()
            val r: Suggestions? = gson.fromJson(s1, Suggestions::class.java)
            return r
        } catch (e: JsonMappingException) {
            e.printStackTrace()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return null
    }
}
