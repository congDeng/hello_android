package com.thoughtworks.helloandroidview.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.helloandroidview.R

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shared_preference)

        initUi()
    }

    private fun initUi() {
        val isHintShown = readSharedPref()
        val tipsView = findViewById<RelativeLayout>(R.id.tips)
        val welcomeView = findViewById<TextView>(R.id.welcome)
        welcomeView.visibility = if (isHintShown) View.VISIBLE else View.GONE
        tipsView.visibility = if (isHintShown) View.GONE else View.VISIBLE

        val button = findViewById<Button>(R.id.got_it)
        button.setOnClickListener {
            writeSharedPref()
            finish()
        }
    }

    private fun readSharedPref(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return false
        return sharedPref.getBoolean(getString(R.string.is_hint_shown), false)
    }

    private fun writeSharedPref() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.is_hint_shown), true)
            apply()
        }
    }
}