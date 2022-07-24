package ru.neonzoff.guidevologdaclient.splash

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.neonzoff.guidevologdaclient.CONFIGURED
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.GUIDEVOLOGDA
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.MainActivity
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    private val DURATION = 1000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SHAREDPREFERENCES = getSharedPreferences(GUIDEVOLOGDA, MODE_PRIVATE)

        if (SHAREDPREFERENCES.getBoolean(CONFIGURED, false)) {
            hideSettings()
            startAnimation()
        }
    }

    fun setPrefs(view: View) {
        when (binding.langGroup.checkedRadioButtonId) {
            binding.langRus.id -> SHAREDPREFERENCES.edit().putString(LANGUAGE, RUSSIAN).apply()
            binding.langEng.id -> SHAREDPREFERENCES.edit().putString(LANGUAGE, ENGLISH).apply()
            else -> SHAREDPREFERENCES.edit().putString(LANGUAGE, ENGLISH).apply()
        }
        SHAREDPREFERENCES.edit().putBoolean(CONFIGURED, true).apply()
        hideSettings()
        startAnimation()
    }

    private fun startAnimation() {
//        if (isOnline()) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.progressBar.max = 100
            val value = 100
            ObjectAnimator.ofInt(binding.progressBar, "progress", value).setDuration(DURATION)
                .start()
            delay(DURATION)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
//        } else {
//            Toast.makeText(this, "Need internet connection", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun hideSettings() {
        binding.tvSettings.visibility = View.GONE
        binding.tvLang.visibility = View.GONE
        binding.langGroup.visibility = View.GONE
        binding.langRus.visibility = View.GONE
        binding.langEng.visibility = View.GONE
        binding.btnSet.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}