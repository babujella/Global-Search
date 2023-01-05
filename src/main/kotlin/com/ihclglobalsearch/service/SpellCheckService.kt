package com.ihclglobalsearch.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihclglobalsearch.dbconfig.Configuration
import com.ihclglobalsearch.model.Spell
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get

class SpellCheckService {
    val props=Configuration.env
    suspend fun spellchecker(params: String): Spell {
        val client = HttpClient(CIO)
        val response = client.get(props.spellCheckURL) {
            url {
                parameters.append("spellcheck.collate", "true")
                parameters.append("spellcheck.q", params)
                parameters.append("spellcheck", "true")
                parameters.append("wt", "JSON")
            }
        }.body<String>()
        val collectionType = object : TypeToken<Spell>() {}.type
        val res: Spell = Gson().fromJson(response, collectionType) as Spell
        return res
       // val respon=res.spellcheck?.suggestions?.get(2)
    }
}