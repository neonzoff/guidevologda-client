package ru.neonzoff.guidevologdaclient.api.dto

data class HomeEntityDto(
    val id: Long,
    val name: String,
    val nameEn: String,
    val description: String,
    val descriptionEn: String,
    val images: List<ImageDto>
)