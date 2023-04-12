package com.thomaskioko.trakt.service.implementation

import co.touchlab.kermit.Logger as KLogger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.EMPTY
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

typealias TraktHttpClient = HttpClient
typealias TraktHttpClientEngine = HttpClientEngine
typealias TraktJson = Json

fun traktHttpClient(
    isDebug: Boolean = false,
    json: TraktJson,
    httpClientEngine: TraktHttpClientEngine,
) = HttpClient(httpClientEngine) {
    install(ContentNegotiation) {
        json(json = json)
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = "api.trakt.tv"
        }
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 60000
        connectTimeoutMillis = 60000
        socketTimeoutMillis = 60000
    }


    install(Logging) {
        level = LogLevel.INFO
        logger = if (isDebug) {
            object : Logger {
                override fun log(message: String) {
                    KLogger.d { message }
                }
            }

        } else {
            Logger.EMPTY
        }
    }
}