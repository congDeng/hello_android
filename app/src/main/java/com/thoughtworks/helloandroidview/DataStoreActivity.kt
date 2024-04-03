package com.thoughtworks.helloandroidview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {
    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shared_preference)

        initUi()
        observeDataFlow()
    }

    private fun initUi() {
        val button = findViewById<Button>(R.id.got_it)
        button.setOnClickListener {
            lifecycleScope.launch {
                updateDataStore()
                finish()
            }
        }
    }

    private fun observeDataFlow() {
        lifecycleScope.launch {
            dataStore.data.collect { preferences ->
                val isHintShow = preferences[DataStoreKeys.IS_HINT_SHOWN] ?: false
                handleUi(isHintShow)
            }
        }
    }

    private fun handleUi(isHintShown: Boolean) {
        val tipsView = findViewById<RelativeLayout>(R.id.tips)
        val welcomeView = findViewById<TextView>(R.id.welcome)
        welcomeView.visibility = if (isHintShown) View.VISIBLE else View.GONE
        tipsView.visibility = if (isHintShown) View.GONE else View.VISIBLE
    }

    private suspend fun updateDataStore() {
        dataStore.edit { settings ->
            settings[DataStoreKeys.IS_HINT_SHOWN] = true
        }
    }

    object DataStoreKeys {
        val IS_HINT_SHOWN = booleanPreferencesKey("is_hint_shown")
    }
}


