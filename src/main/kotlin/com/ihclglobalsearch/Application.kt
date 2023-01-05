package com.ihclglobalsearch

import com.ihclglobalsearch.dbconfig.Configuration
import com.ihclglobalsearch.plugin.configureRouting
import com.ihclglobalsearch.plugin.configureSerialization
import io.ktor.server.application.*


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)// application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    Configuration.initConfig(this.environment)
    configureRouting()
    configureSerialization()
}


