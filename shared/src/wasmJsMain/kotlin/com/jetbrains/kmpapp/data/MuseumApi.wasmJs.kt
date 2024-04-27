package com.jetbrains.kmpapp.data

import dev.kilua.rest.RestClient
import dev.kilua.rest.call
import io.ktor.client.*
import kotlin.coroutines.cancellation.CancellationException

class KiluaMuseumApi : MuseumApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template/main/list.json"
        private val client = RestClient()
    }

    override suspend fun getData(): List<MuseumObject> {
        return try {
            client.call(API_URL) {
                contentType = null
                headers = { listOf("accept" to "*/*")}
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }

    }
}

actual fun getMuseumApi(client: HttpClient): MuseumApi {
    return KiluaMuseumApi()
}
