package com.jetbrains.kmpapp.data

import io.ktor.client.*

actual fun getMuseumApi(client: HttpClient): MuseumApi {
    return KtorMuseumApi(client)
}