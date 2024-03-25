package com.thoughtworks.helloandroidview

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.toast_button).setOnClickListener {
            val myToast = Toast.makeText(applicationContext, "Hello Toast!", Toast.LENGTH_SHORT)
            myToast.show()
        }
        findViewById<Button>(R.id.count_button).setOnClickListener {
            countMe()
        }
    }

    private fun countMe(){
        val showCountTextView = findViewById<TextView>(R.id.textView)
        val countString = showCountTextView.text.toString()
        var count = countString.toInt()
        count++
        showCountTextView.text = count.toString()
    }
}