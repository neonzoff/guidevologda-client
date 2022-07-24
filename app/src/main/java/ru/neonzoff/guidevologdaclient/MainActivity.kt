package ru.neonzoff.guidevologdaclient

import android.content.ClipboardManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.yandex.mapkit.MapKitFactory
import ru.neonzoff.guidevologdaclient.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CLIPBOARDMANAGER = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        APP = this
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
//        binding.bottomNavView.itemIconTintList = null
        MapKitFactory.setApiKey(API_KEY_MAPKIT)
    }

    fun changeLanguage(view: View) {
        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
            RUSSIAN -> SHAREDPREFERENCES.edit().putString(LANGUAGE, ENGLISH).apply()
            ENGLISH -> SHAREDPREFERENCES.edit().putString(LANGUAGE, RUSSIAN).apply()
        }
    }
}