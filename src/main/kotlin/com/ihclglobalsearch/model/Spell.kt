package com.ihclglobalsearch.model

import com.google.gson.annotations.SerializedName


data class Spell (
    var responseHeader: SpellResponseHeader? = null,
    var response: SpellResponse? = null,
    var spellcheck: Spellcheck? = null
)

data class SpellResponse (
    var numFound: Int = 0,
    var start: Int = 0,
    var numFoundExact: Boolean = false,
    var docs: ArrayList<Any>? = null
)

data class SpellResponseHeader (
    var zkConnected: Boolean = false,
    var status: Int = 0,
    @SerializedName("QTime")
    var qTime: Int = 0
)

data class Spellcheck (
    var suggestions: ArrayList<Any>? = null,
    var correctlySpelled: Boolean = false,
    var collations: ArrayList<Any>? = null
)
