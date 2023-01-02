package com.ihcl_globalsearch

import com.ihcl_globalsearch.route.Configuremodules
import com.ihcl_globalsearch.route.configureDestinationsRouting
import com.ihcl_globalsearch.route.spatialSearchRoute
import configureHotelRestaurantSpaRouting
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    Configuremodules()

}


