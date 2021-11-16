package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.OnWindowSelectedListener
import com.faircorp.model.services.ApiServices
import com.faircorp.model.services.WindowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WindowActivity : BasicActivity(), OnWindowSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val windowName = findViewById<TextView>(R.id.txt_window_name)

        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().windowsApiService.findById(id).execute(); } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) {

                        val window = it.body();

                        if (window != null) {
                            findViewById<TextView>(R.id.txt_window_name).text = window.name
                            findViewById<TextView>(R.id.txt_window_status).text = window.windowStatus.toString()
                            findViewById<TextView>(R.id.txt_room_name).text = window.roomName
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onWindowSelected(id: Long) {
        TODO("Not yet implemented")
    }




}