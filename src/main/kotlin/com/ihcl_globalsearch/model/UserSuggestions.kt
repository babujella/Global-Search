package com.ihcl_globalsearch.route


data class Suggestions (
    var numFound: Int = 0,
    var suggestions: ArrayList<SuggestionTerms>? = null)

data class SuggestionTerms (
    var term: String? = null,
    var weight: Int = 0,
    var payload: String? = null
)



