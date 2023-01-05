package com.ihclglobalsearch.dbconfig

import com.ihclglobalsearch.model.ConfigParameters
import io.ktor.server.application.*

object Configuration {
    lateinit var env: ConfigParameters

    fun initConfig(environment: ApplicationEnvironment){
        env= ConfigParameters(
            destinationURL =environment.config.property("ktor.api.destinationURL").getString(),
            spatialSearchURL = environment.config.property("ktor.api.spatialSearchURL").getString(),
            spellCheckURL = environment.config.property("ktor.api.spellCheckURL").getString(),
            suggestionsURL= environment.config.property("ktor.api.suggestionsURL").getString()
        )
    }
}