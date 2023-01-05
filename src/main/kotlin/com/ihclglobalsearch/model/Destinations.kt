package com.ihclglobalsearch.model

import com.google.gson.annotations.SerializedName

data class Destinations(
    var responseHeader:ResponseHeader,
    var response:Response
)


data class  Doc(
    var city:ArrayList<String>
)

data class Params(
    var q:String,
    var indent:String,
    var fl:String,
    @SerializedName("q.op")
    var `qop` :String
)

data class Response(
    var numFound:Int,
    var start:Int,
    var numFoundExact:Boolean,
    var docs :ArrayList<Doc>
)

data class ResponseHeader(
    var zkConnected:Boolean,
    var status:Int,
    @SerializedName("QTime")
    var qTime:Int,
    var params: Params
)