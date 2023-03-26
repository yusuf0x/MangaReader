package com.manga.mangareader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.manga.mangareader.databinding.ActivityMainBinding
import com.manga.mangareader.ui.browse.BrowseFragment
import com.manga.mangareader.ui.library.LibraryFragment
import com.manga.mangareader.ui.search.SearchFragment
import com.manga.mangareader.ui.settings.SettingFragment

class MainActivity : AppCompatActivity(){
    private lateinit var floatingActionButton: FloatingActionButton
    private  lateinit var  binding : ActivityMainBinding
    private lateinit var bottomNavigation: MeowBottomNavigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MTPreferences.getThemeMode(applicationContext))
        setTheme(ThemeHelper.getTheme(MTPreferences.getTheme(applicationContext))!!);
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.nav_view_main)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_browse))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_library))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_search))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_setting))
        bottomNavigation.show(1)
        addFragment()
        bottomNavigation.setOnClickMenuListener {
            replaceFragment(it)
        }

    }
    private fun replaceFragment(model: MeowBottomNavigation.Model) {
        var fragment: Fragment? = null
        when (model.id) {
            1 -> fragment = BrowseFragment.newInstance()
            2 -> fragment = LibraryFragment()
            3 -> fragment = SearchFragment()
            4 -> fragment = SettingFragment()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout_main, fragment!!)
            .commit()
    }
    private fun addFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout_main, BrowseFragment.newInstance())
            .commit()
    }
}