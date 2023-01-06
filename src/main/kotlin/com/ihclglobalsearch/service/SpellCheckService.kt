package com.ihclglobalsearch.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihclglobalsearch.dbconfig.Configuration
import com.ihclglobalsearch.model.Spell
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.log

class SpellCheckService {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    val props=Configuration.env
    suspend fun spellchecker(param: String): Spell {
        val client = HttpClient(CIO)
        val response = client.get(props.spellCheckURL) {
            url {
                parameters.append("spellcheck.q",param)
            }
        }.body<String>()
       log.info(response)
        val collectionType = object : TypeToken<Spell>() {}.type
        val res: Spell = Gson().fromJson(response, collectionType) as Spell
        return res
       // val respon=res.spellcheck?.suggestions
    }
}