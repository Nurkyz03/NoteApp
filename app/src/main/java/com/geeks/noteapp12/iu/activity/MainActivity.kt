package com.geeks.noteapp12.iu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.geeks.noteapp12.R
import com.geeks.noteapp12.databinding.ActivityMainBinding
import com.geeks.noteapp12.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        if (sharedPreferences.isOnBoardShown) {
            navController.navigate(R.id.noteFragment)
        } else if (!sharedPreferences.isOnBoardShown) {
            navController.navigate(R.id.onBoardFragment)
        }
    }
}