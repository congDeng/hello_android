package com.thoughtworks.helloandroidview.view

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.thoughtworks.helloandroidview.R
import com.thoughtworks.helloandroidview.view.fragment.AndroidFragment
import com.thoughtworks.helloandroidview.view.fragment.JavaFragment

class LanguageSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_language_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUi()
    }

    private fun initUi() {
        navigateToFragment(supportFragmentManager, AndroidFragment(), R.id.content)

        val androidButton = findViewById<Button>(R.id.android_button)
        androidButton.setOnClickListener {
            navigateToFragment(supportFragmentManager, AndroidFragment(), R.id.content)
        }

        val javaButton = findViewById<Button>(R.id.java_button)
        javaButton.setOnClickListener {
            navigateToFragment(supportFragmentManager, JavaFragment(), R.id.content)
        }
    }

    private fun navigateToFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        fragmentId: Int
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentId, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }
}