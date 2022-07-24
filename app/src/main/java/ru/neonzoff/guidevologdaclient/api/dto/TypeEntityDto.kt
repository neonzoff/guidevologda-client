package ru.neonzoff.guidevologdaclient.api.dto

import java.io.Serializable

data class TypeEntityDto(
    val id: Long,
    val name: String,
    val nameEn: String,
    val description: String,
    val descriptionEn: String,
    val image: String
) : Serializable