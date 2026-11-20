package api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CatFactDataDto(
    val fact: String,
    val length: Int
)
