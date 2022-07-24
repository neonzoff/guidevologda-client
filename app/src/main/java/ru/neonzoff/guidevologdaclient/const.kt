package ru.neonzoff.guidevologdaclient

import android.content.ClipboardManager
import android.content.SharedPreferences
import com.yandex.mapkit.mapview.MapView


lateinit var APP: MainActivity
lateinit var CLIPBOARDMANAGER: ClipboardManager
lateinit var SHAREDPREFERENCES: SharedPreferences
lateinit var mapView: MapView
var positions: List<String>? = null

val GUIDEVOLOGDA: String = "GUIDEVOLOGDA"
val URL_CLOUD: String = "https://storage.yandexcloud.net/project_name/"
val API_KEY_MAPKIT: String = "API_KEY"

val LANGUAGE: String = "LANGUAGE"
val RUSSIAN: String = "RUSSIAN"
val ENGLISH: String = "ENGLISH"

val CONFIGURED: String = "CONFIGURED"

val TYPE_ENTITY: String = "typeEntity"
val ENTITY: String = "entity"
val TRACK: String = "track"
