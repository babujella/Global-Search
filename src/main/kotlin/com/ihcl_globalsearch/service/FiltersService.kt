package com.ihcl_globalsearch.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihcl_globalsearch.model.Destinations1
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

class FiltersService {
    suspend fun filterservice(brand_name:String, hotel_name:String, pms_name:String): Destinations1 {
        val client = HttpClient(CIO)
        val response: String = client.get("http://localhost:8983/solr/Ihcls/select?&indent=true&q.op=OR&rows=200&"){
            url {
                parameters.append("q","hotel_name:$hotel_name")
                parameters.append("fq","brand_name:$brand_name")
                parameters.append("fq","pms_name:$pms_name")
            }
        }.body()
        val collectionType = object : TypeToken<Destinations1>() {}.type
        val res: Destinations1 = Gson().fromJson(response, collectionType) as Destinations1
        return res
    }
}