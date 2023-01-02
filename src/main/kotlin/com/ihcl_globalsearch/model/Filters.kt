package com.ihcl_globalsearch.model

import com.google.gson.annotations.SerializedName


data class Filters(
    var brand_name :String,
    var pms_name :String,
    var hotel_name:String
)

data class FiltersS (
    var brand_name: ArrayList<String>? = null,
    var brand_key: ArrayList<Int>? = null,
    var hotel_id: ArrayList<String>? = null,
    var hotel_code: ArrayList<String>? = null,
    var hotel_name: ArrayList<String>? = null,
    var hotel_type: ArrayList<String>? = null
)

data class Params1(
    var q:String,
    var indent:String,
    var fl:String,
    @SerializedName("q.op")
    var `qop` :String
)

data class Response1(
    var numFound:Int,
    var start:Int,
    var numFoundExact:Boolean,
    var docs :ArrayList<FiltersS>
)

data class ResponseHeader1(
    var zkConnected:Boolean,
    var status:Int,
    @SerializedName("QTime")
    var qTime:Int,
    var params: Params1
)

data class Destinations1(
    var responseHeader:ResponseHeader1,
    var response:Response1
)
