package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.view.View

import android.widget.Toast
import com.faircorp.model.OnWindowSelectedListener
import com.faircorp.model.adapters.WindowAdapter
import com.faircorp.model.services.ApiServices
import com.faircorp.model.services.WindowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class WindowsActivity : BasicActivity(), OnWindowSelectedListener  {
    var roomId : Long =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)
        val windowService = WindowService() // (1)

        val id = intent.getLongExtra("roomId",0)
        roomId=id
        println("id room"+id)
        val recyclerView = findViewById<RecyclerView>(R.id.list_windows) // (2)
        val adapter = WindowAdapter(this) // (3)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        updateRecycler(adapter)

    }


        //adapter.update(windowService.findAll()) // (4)
        private fun updateRecycler(adapter: WindowAdapter) {
            val id = intent.getLongExtra(ROOM_NAME_PARAM,0)
            println("id room"+id)
            lifecycleScope.launch(context = Dispatchers.IO) { // (1)
                runCatching { ApiServices().windowsApiService.findRoomWindows(roomId).execute() } // (2)
                    .onSuccess {
                        withContext(context = Dispatchers.Main) { // (3)
                            adapter.update(it.body() ?: emptyList())
                            println("Windows found:")
                            println(it.body())
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

    fun openWindow(id: Long) {
        //Do something in response to button
        val intent = Intent(this, WindowActivity::class.java).apply {  }.putExtra(WINDOW_NAME_PARAM,id)
        startActivity(intent)
    }


    override fun onWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }


}