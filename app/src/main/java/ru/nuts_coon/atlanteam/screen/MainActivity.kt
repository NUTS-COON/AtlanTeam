package ru.nuts_coon.atlanteam.screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.nuts_coon.atlanteam.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    private val list: List<Fragment>
        get() {
            return listOf<Fragment>(CardsFragment(), ContactsFragment())
        }

    private fun setupViewPager(){
        val fragmentList = list
        val adapter = FragmentAdapter(supportFragmentManager, fragmentList, listOf("Cards", "Contacts"))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
