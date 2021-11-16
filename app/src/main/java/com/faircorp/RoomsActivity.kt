package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.model.OnRoomSelectedListener
import com.faircorp.model.adapters.BuildingsAdapter
import com.faircorp.model.adapters.RoomsAdapter
import com.faircorp.model.services.ApiServices
import com.faircorp.model.services.RoomService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RoomsActivity : BasicActivity(), OnRoomSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)
        //we obtain the id of the room selected
        val recyclerView = findViewById<RecyclerView>(R.id.list_rooms) // (2)
        val adapter = RoomsAdapter(this) // (3)
        val id = intent.getLongExtra(BUILDING_NAME_PARAM,0)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().roomApiService.findBuildingRooms(id).execute()} // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        adapter.update(it.body() ?: emptyList())
                        println("Rooms found:")
                        println(it.body())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on rooms loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }


    }


    override fun onRoomSelected(id: Long) {
        val intent = Intent(this, RoomActivity::class.java).putExtra(ROOM_NAME_PARAM, id)
        startActivity(intent)
    }
}