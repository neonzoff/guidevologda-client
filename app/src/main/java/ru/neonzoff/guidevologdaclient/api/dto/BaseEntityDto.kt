package ru.neonzoff.guidevologdaclient.api.dto

import java.io.Serializable

data class BaseEntityDto(
    val id: Long,
    val typeEntity: Long,
    val name: String,
    val nameEn: String,
    val description: String,
    val descriptionEn: String,
    val street: Long,
    val houseNumber: String,
    val pos: String,
    val images: List<ImageDto>,
    val contacts: List<ContactDto>,
    val properties: List<EntityPropertiesDto>,
    val workSchedule: String,
    val workScheduleEn: String,
    val tags: List<Long>,
    val tracks: List<Long>,
    val active: Boolean
) : Serializable