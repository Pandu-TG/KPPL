package com.stigma15.pandu.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stigma15.pandu.Fragment.FavoriteFragment
import com.stigma15.pandu.Fragment.HomeFragment
import com.stigma15.pandu.Fragment.SearchFragment
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var favoriteFragment: FavoriteFragment
    lateinit var searchFragment: SearchFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*homeFragment = HomeFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
*/
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bot_nav)
        //val appBarConfig = AppBarConfiguration(setOf(R.id.homeFragment, R.id.searchFragment, R.id.favoriteFragment))
        //setupActionBarWithNavController(navController, appBarConfig)
        val navController = findNavController(R.id.fragment)
        bottomNavigation.setupWithNavController(navController)

        /*
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.homeFragment ->{
                    navController.navigate(R.id.action_homeFragment_to_searchFragment)
                }
                R.id.searchFragment ->{
                    navController.navigate(R.id.action_homeFragment_to_searchFragment)
                }
                R.id.favoriteFragment ->{
                    navController.navigate(R.id.ac)
                }
            }
            true

        }*/
        /*bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.home ->{
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.frame_layout, homeFragment)
                        .commit()
                }
                R.id.search ->{
                    searchFragment = SearchFragment()
                    supportFragmentManager.commit {setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        replace(R.id.frame_layout, searchFragment)
                        addToBackStack(null)}
                }
                R.id.favorite ->{
                    favoriteFragment = FavoriteFragment()
                        supportFragmentManager.commit {setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        replace(R.id.frame_layout, favoriteFragment)
                            addToBackStack(null)
                        }
                }
            }
            true

        }*/

        //Hide status bar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21)
        {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        if (Build.VERSION.SDK_INT >= 21)
        {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            getWindow().setStatusBarColor(Color.TRANSPARENT)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    fun setWindowFlag(activity: Activity, bits:Int, on:Boolean) {
        val win = activity.getWindow()
        val winParams = win.getAttributes()
        if (on)
        {
            winParams.flags = winParams.flags or bits
        }
        else
        {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.setAttributes(winParams)
    }
    fun searchPage(){
        val navController = findNavController(R.id.fragment)
        navController.navigate(R.id.action_homeFragment_to_searchFragment)
    }

}