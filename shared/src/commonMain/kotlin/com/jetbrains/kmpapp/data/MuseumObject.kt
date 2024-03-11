package com.jetbrains.kmpapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

var useCorsWorkaround = false

@Serializable
data class MuseumObject(
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    val medium: String,
    val dimensions: String,
    val objectURL: String,
    val objectDate: String,
    @SerialName("primaryImage") private val primaryImageUrl: String,
    @SerialName("primaryImageSmall") private val primaryImageSmallUrl: String,
    val repository: String,
    val department: String,
    val creditLine: String,
) {
    val primaryImage: String
        get() = if (useCorsWorkaround) {
            "http://localhost:8080/${primaryImageUrl.substringAfter("https://images.metmuseum.org/")}"
        } else {
            primaryImageUrl
        }

    val primaryImageSmall: String
        get() = if (useCorsWorkaround) {
            "http://localhost:8080/${primaryImageSmallUrl.substringAfter("https://images.metmuseum.org/")}"
        } else {
            primaryImageSmallUrl
        }
}
