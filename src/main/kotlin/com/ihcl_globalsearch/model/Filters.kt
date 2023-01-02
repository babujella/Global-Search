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


object RemoveDuplicateInArrayExample {
    fun removeDuplicateElements(arr: IntArray, n: Int): Int {
        if (n == 0 || n == 1) {
            return n
        }
        val temp = IntArray(n)
        var j = 0
        for (i in 0 until n - 1) {
            if (arr[i] != arr[i + 1]) {
                temp[j++] = arr[i]
            }
        }
        temp[j++] = arr[n - 1]
        // Changing original array
        for (i in 0 until j) {
            arr[i] = temp[i]
        }
        return j
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(10, 20, 20, 30, 30, 40, 50, 50)
        var length = arr.size
        length = removeDuplicateElements(arr, length)
        //printing array elements
        for (i in 0 until length) print(arr[i].toString() + " ")
    }

    object RemoveDuplicateArrayList {
        @JvmStatic
        fun main(args: Array<String>) {
            val l: MutableList<String> = ArrayList()
            l.add("Mango")
            l.add("Banana")
            l.add("Mango")
            l.add("Apple")
            println(l.toString())
            val s: Set<String> = LinkedHashSet(l)
            println(s)
        }
    }
}
