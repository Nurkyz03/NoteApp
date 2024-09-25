package com.geeks.noteapp12.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.geeks.noteapp12.R
import com.geeks.noteapp12.databinding.ActivityMainBinding
import com.geeks.noteapp12.ui.service.MyFirebaseMessagingService.Companion.PERMISSION_REQUEST_CODE
import com.geeks.noteapp12.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)

        if (sharedPreferences.isOnBoardShown) {
            if (sharedPreferences.isSignInShowed) {
                navController.navigate(R.id.action_signUpFragment_to_noteFragment)
            } else {
                navController.navigate(R.id.signUpFragment)
            }
        } else {
            navController.navigate(R.id.onBoardFragment)
        }
        requestNotificationPermission()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
            PERMISSION_REQUEST_CODE
        )
    }
}