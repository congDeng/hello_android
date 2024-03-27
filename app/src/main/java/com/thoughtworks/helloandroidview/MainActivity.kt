package com.thoughtworks.helloandroidview

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
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
        generateButtons()
    }

    private fun generateButtons() {
        val layout = findViewById<LinearLayout>(R.id.linear_layout)

        for (number in 1..20) {
            val button = Button(this)
            button.text = "${getString(R.string.button_name)} $number"
            val layoutParams = ViewGroup.MarginLayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0,16,0,0)
            button.layoutParams = layoutParams

            if(number == 1){
                button.setOnClickListener{
                    val intent = Intent(this@MainActivity, ConstraintActivity::class.java)
                    startActivity(intent)
                }
            }

            layout.addView(button)
        }
    }
}