package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.faircorp.model.OnRoomSelectedListener
import com.faircorp.model.OnWindowSelectedListener
import com.faircorp.model.adapters.RoomsAdapter
import com.faircorp.model.adapters.WindowAdapter
import com.faircorp.model.services.RoomService
import com.faircorp.model.services.WindowService

class RoomsActivity : BasicActivity(), OnRoomSelectedListener {
    val roomService = RoomService() // (1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        val recyclerView = findViewById<RecyclerView>(R.id.list_rooms) // (2)
        val adapter = RoomsAdapter(this) // (3)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        adapter.update(roomService.findAll()) // (4)

    }

    override fun onRoomSelected(id: Long) {
        val intent = Intent(this, RoomActivity::class.java).putExtra(ROOM_NAME_PARAM, id)
        startActivity(intent)
    }
}