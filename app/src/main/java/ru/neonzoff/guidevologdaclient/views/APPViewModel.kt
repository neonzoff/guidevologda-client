package ru.neonzoff.guidevologdaclient.views

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.neonzoff.guidevologdaclient.api.Repository
import ru.neonzoff.guidevologdaclient.api.dto.BaseEntityDto
import ru.neonzoff.guidevologdaclient.api.dto.ContactTypeDto
import ru.neonzoff.guidevologdaclient.api.dto.HomeEntityDto
import ru.neonzoff.guidevologdaclient.api.dto.StreetDto
import ru.neonzoff.guidevologdaclient.api.dto.TrackDto
import ru.neonzoff.guidevologdaclient.api.dto.TypeEntityDto

class APPViewModel : ViewModel() {

    val repository = Repository()
    val homeEntity: MutableLiveData<Response<HomeEntityDto>> = MutableLiveData()
    val entity: MutableLiveData<Response<BaseEntityDto>> = MutableLiveData()
    val entities: MutableLiveData<Response<List<BaseEntityDto>>> = MutableLiveData()
    val contactTypes: MutableLiveData<Response<List<ContactTypeDto>>> = MutableLiveData()
    val typesEntity: MutableLiveData<Response<List<TypeEntityDto>>> = MutableLiveData()
    val tracks: MutableLiveData<Response<List<TrackDto>>> = MutableLiveData()
    val track: MutableLiveData<Response<TrackDto>> = MutableLiveData()
    val streets: MutableLiveData<Response<List<StreetDto>>> = MutableLiveData()
    val street: MutableLiveData<Response<StreetDto>> = MutableLiveData()

    fun getHomeEntity() {
        viewModelScope.launch {
            try {
                homeEntity.value = repository.getHomeEntity()
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getHomeEntity()")
            }
        }
    }

    fun getEntity(id: Long) {
        viewModelScope.launch {
            try {
                entity.value = repository.getEntity(id)
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getEntity(id: Long)")
            }
        }
    }

    fun getEntities(typeEntityId: Long) {
        viewModelScope.launch {
            try {
                entities.value = repository.getEntities(typeEntityId)
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message}  in method getEntities(typeEntityId: Long)")
            }
        }
    }

    fun getEntities() {
        viewModelScope.launch {
            try {
                entities.value = repository.getEntities()
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getEntities()")
            }
        }
    }

    fun getTypesEntity() {
        viewModelScope.launch {
            try {
                typesEntity.value = repository.getTypesEntity()
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getTypesEntity()")
            }
        }
    }

    fun getContactTypes() {
        viewModelScope.launch {
            try {
                contactTypes.value = repository.getContactTypes()
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getContactTypes()")
            }
        }
    }

    fun getTracks() {
        viewModelScope.launch {
            try {
                tracks.value = repository.getTracks()
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getTracks()")
            }
        }
    }

    fun getTrack(id: Long) {
        viewModelScope.launch {
            try {
                track.value = repository.getTrack(id)
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getTrack(${id})")
            }
        }
    }

    fun getStreets() {
        viewModelScope.launch {
            try {
                streets.value = repository.getStreets()
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getStreets()")
            }
        }
    }

    fun getStreet(id: Long) {
        viewModelScope.launch {
            try {
                street.value = repository.getStreet(id)
            } catch (e: Exception) {
                Log.d(TAG, "Exception ${e.message} in method getStreet(${id})")
            }
        }
    }

}