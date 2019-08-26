package com.example.majournee.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.majournee.R
import com.example.majournee.fragments.MusicFragment
import com.example.majournee.fragments.ShopFragment
import com.example.majournee.fragments.WeatherFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setNavDrawer()
        setHeaderInfo()

        if (savedInstanceState == null) {
            nav_view.menu.getItem(0).isChecked = true
            fragmentTransaction(WeatherFragment())
        }
    }

    private fun setNavDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
            R.string.open_drawer,
            R.string.closed_drawer
        )
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

    private fun setHeaderInfo() {
        val name = nav_view.getHeaderView(0).findViewById<TextView>(R.id.tv_header_name)
        val email = nav_view.getHeaderView(0).findViewById<TextView>(R.id.tv_header_mail)

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_mail) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_weather -> fragmentTransaction(WeatherFragment())
            R.id.nav_top_music -> fragmentTransaction(MusicFragment())
            R.id.nav_shop -> fragmentTransaction(ShopFragment())
            R.id.nav_sign_out -> this.longToast(R.string.sign_out)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()
    }
}
