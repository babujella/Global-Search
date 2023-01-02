package com.ihcl_globalsearch.model

import com.google.gson.annotations.SerializedName


data class SpatialSearch(
    var pt:String,
    var d:String,
    var destination:City

)
data class City(
    var city:String,
    var brand_name:String
)


data class Doc2 (
    var hotel_name :ArrayList<Any>?=null,
    var city: ArrayList<Any>? = null
)
data class Params2 (
    var q: String? = null,
    var pt: String? = null,
    var d: String? = null,
    var fq: ArrayList<String>? = null
)

data class Response2 (
    var numFound: Int = 0,
    var start: Int = 0,
    var numFoundExact: Boolean = false,
    var docs: ArrayList<Doc2>? = null
)

data class ResponseHeader2 (
    var zkConnected: Boolean = false,
    var status: Int = 0,
    @SerializedName("QTime")
    var qTime: Int = 0,
    var params: Params2? = null
)

data class Spatial (
    var responseHeader: ResponseHeader2? = null,
    var response: Response2? = null
)




