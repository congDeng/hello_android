package com.thoughtworks.helloandroidview

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ThreadActivity : AppCompatActivity() {
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_thread)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUi()
    }

    private fun initUi() {
        button = findViewById(R.id.button)
        button.text = "0"
        button.setOnClickListener {
            CounterTask().execute()
        }
    }


    private inner class CounterTask : AsyncTask<Void, Int?, Void>() {
        override fun onPreExecute() {
            super.onPreExecute()
            button.text = "0"
            button.isEnabled = false
        }

        override fun doInBackground(vararg params: Void): Void? {
            for (count in 1..10){
                Thread.sleep(1000)
                publishProgress(count)
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            button.text = values[0].toString()
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            button.isEnabled = true
        }

    }


}