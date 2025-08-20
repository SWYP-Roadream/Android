package com.yeogijeogi.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.RequestBody.Companion.toRequestBody

@Serializable
data class SignUpRequest(
    val provider: String,
    val providerUserId: String,
    val imageUrl: String?,
    val nickname: String,
    val ageGroup: String,
    val companions: List<String>,
    val mbti: String?
) {
    fun toRequestBody() = Json.encodeToString(this).toRequestBody()
}
