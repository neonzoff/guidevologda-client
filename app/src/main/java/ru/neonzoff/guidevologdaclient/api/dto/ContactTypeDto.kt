package ru.neonzoff.guidevologdaclient.api.dto

data class ContactTypeDto(
    val id: Long,
    val name: String,
    val nameEn: String,
    val contacts: List<ContactDto>
)