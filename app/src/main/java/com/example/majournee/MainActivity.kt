package com.example.majournee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.majournee.fragments.MusicFragment
import com.example.majournee.fragments.ShopFragment
import com.example.majournee.fragments.WeatherFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setNavDrawer()
        nav_view.menu.getItem(0).isChecked = true
        fragmentTransaction(WeatherFragment())
    }

    private fun setNavDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open_drawer, R.string.closed_drawer)
        toggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun fragmentTransaction(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_weather -> fragmentTransaction(WeatherFragment())
            R.id.nav_top_music -> fragmentTransaction(MusicFragment())
            R.id.nav_shop -> fragmentTransaction(ShopFragment())
        }
        return true
    }
}
