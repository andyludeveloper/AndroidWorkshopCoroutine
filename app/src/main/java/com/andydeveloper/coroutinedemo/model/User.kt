package com.andydeveloper.coroutinedemo.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "url") val url: String
)