package ru.neonzoff.guidevologdaclient.api.dto

import java.io.Serializable

data class TrackDto(
    val id: Long,
    val name: String,
    val nameEn: String,
    val description: String,
    val descriptionEn: String,
    val image: ImageDto,
    val entities: List<BaseEntityDto>
) : Serializable