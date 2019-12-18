package com.kanykeinu.chocoorder.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.kanykeinu.chocoorder.R

class MainActivity : AppCompatActivity() {

    private var currentController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentController =
            Navigation.findNavController(findViewById(R.id.container))
//        NavigationUI.setupActionBarWithNavController(this, currentController)
    }

    override fun onBackPressed() {
        if (!currentController!!.popBackStack()) {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentController!!.navigateUp()
    }
}
