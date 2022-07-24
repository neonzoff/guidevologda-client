package ru.neonzoff.guidevologdaclient.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.neonzoff.guidevologdaclient.api.dto.BaseEntityDto
import ru.neonzoff.guidevologdaclient.api.dto.ContactTypeDto
import ru.neonzoff.guidevologdaclient.api.dto.HomeEntityDto
import ru.neonzoff.guidevologdaclient.api.dto.StreetDto
import ru.neonzoff.guidevologdaclient.api.dto.TrackDto
import ru.neonzoff.guidevologdaclient.api.dto.TypeEntityDto

interface ApiService {

    @GET("homeentity")
    suspend fun getHomeEntity(): Response<HomeEntityDto>

    /**
     * возвращает объектов по id
     *
     * @param id идентификатор объекта
     * @return объект
     */
    @GET("entities/{id}")
    suspend fun getEntity(@Path("id") id: Long): Response<BaseEntityDto>

    /**
     * возвращает список объектов с параметрами названия тега и айди типа объекта
     *
     * @return список объектов
     */
    @GET("entities")
    suspend fun getEntities(
//        @Query("tag") tag: String,
        @Query("typeEntityId") typeEntityId: Long
    ): Response<List<BaseEntityDto>>

    /**
     * возвращает список объектов
     *
     * @return список объектов
     */
    @GET("entities")
    suspend fun getEntities(): Response<List<BaseEntityDto>>

    /**
     * возвращает список типов объектов
     *
     * @return список типов объектов
     * */
    @GET("typeentity")
    suspend fun getTypesEntity(): Response<List<TypeEntityDto>>

    /**
     * возвращает список типов контактов
     *
     * @return список типов контактов
     * */
    @GET("contacttypes")
    suspend fun getContactTypes(): Response<List<ContactTypeDto>>

    /**
     * возвращает список треков
     *
     * @return список треков
     * */
    @GET("tracks")
    suspend fun getTracks(): Response<List<TrackDto>>

    /**
     * возвращает трек по id
     *
     * @param id индентификатор трека
     * @return трек
     * */
    @GET("tracks/{id}")
    suspend fun getTrack(@Path("id") id: Long): Response<TrackDto>

    /**
     * возвращает список улиц
     *
     * @return список улиц
     * */
    @GET("streets")
    suspend fun getStreets(): Response<List<StreetDto>>

    /**
     * возвращает улицу по id
     *
     * @param id индентификатор улицы
     * @return ултцу
     * */
    @GET("streets/{id}")
    suspend fun getStreet(@Path("id") id: Long): Response<StreetDto>
}