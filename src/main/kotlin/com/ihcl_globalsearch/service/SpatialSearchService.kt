package com.ihcl_globalsearch.service

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.ihcl_globalsearch.model.Spatial
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*


class SpatialSearchService {
    suspend fun spatialserachservice(pt:String, d:String,destination:String,brand_name:String):Spatial{
        val client = HttpClient(CIO)
        val response: String = client.get("http://localhost:8983/solr/Ihcls/select?&"){
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
        println("$pt $d $destination $brand_name")
        val collectionType = object : TypeToken<Spatial>() {}.type
        val res: Spatial = Gson().fromJson(response, collectionType) as Spatial
        return res

    }
    suspend fun spatialserachservice1(pt:String,d:String):Root{
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
        val res: Root = Gson().fromJson(response, collectionType) as Root
        return  res
    }
}
data class Doc (
    var hotel_name: ArrayList<String>? = null,
    var city: ArrayList<String>? = null
)

data class Params (
    var q: String? = null,
    var pt: String? = null,
    var d: String? = null,
    var fq: String? = null
)

data class Response (
    var numFound: Int = 0,
    var start: Int = 0,
    var numFoundExact: Boolean = false,
    var docs: ArrayList<Doc>? = null
    )

data class ResponseHeader (
    var zkConnected: Boolean = false,
    var status: Int = 0,

    @SerializedName("QTime")
    var qTime: Int = 0,
    var params: Params? = null
    )

class Root (
    var responseHeader: ResponseHeader? = null,
    var response: Response? = null
)
