package ru.neonzoff.guidevologdaclient.api

import retrofit2.Response
import ru.neonzoff.guidevologdaclient.api.dto.BaseEntityDto
import ru.neonzoff.guidevologdaclient.api.dto.ContactTypeDto
import ru.neonzoff.guidevologdaclient.api.dto.HomeEntityDto
import ru.neonzoff.guidevologdaclient.api.dto.StreetDto
import ru.neonzoff.guidevologdaclient.api.dto.TrackDto
import ru.neonzoff.guidevologdaclient.api.dto.TypeEntityDto

class Repository {

    suspend fun getHomeEntity(): Response<HomeEntityDto> {
        return RetrofitConfig.apiService.getHomeEntity()
    }

    suspend fun getEntity(id: Long): Response<BaseEntityDto> {
        return RetrofitConfig.apiService.getEntity(id)
    }

    suspend fun getEntities(typeEntityid: Long): Response<List<BaseEntityDto>> {
        return RetrofitConfig.apiService.getEntities(typeEntityid)
    }

    suspend fun getEntities(): Response<List<BaseEntityDto>> {
        return RetrofitConfig.apiService.getEntities()
    }

    suspend fun getTypesEntity(): Response<List<TypeEntityDto>> {
        return RetrofitConfig.apiService.getTypesEntity()
    }

    suspend fun getContactTypes(): Response<List<ContactTypeDto>> {
        return RetrofitConfig.apiService.getContactTypes()
    }

    suspend fun getTracks(): Response<List<TrackDto>> {
        return RetrofitConfig.apiService.getTracks()
    }

    suspend fun getTrack(id: Long): Response<TrackDto> {
        return RetrofitConfig.apiService.getTrack(id)
    }

    suspend fun getStreets(): Response<List<StreetDto>> {
        return RetrofitConfig.apiService.getStreets()
    }

    suspend fun getStreet(id: Long): Response<StreetDto> {
        return RetrofitConfig.apiService.getStreet(id)
    }

}