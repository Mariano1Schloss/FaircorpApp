package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.faircorp.model.*
import com.faircorp.model.adapters.BuildingsAdapter
import com.faircorp.model.services.BuildingService

const val BUILDING_NAME_PARAM = "com.faircorp.buildingname.attribute"
const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"
const val ROOM_NAME_PARAM = "com.faircorp.roomname.attribute"



class MainActivity : BasicActivity() ,OnBuildingSelectedListener {
    val buildingService = BuildingService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val recyclerView = findViewById<RecyclerView>(R.id.list_buildings) // (2)
        val adapter = BuildingsAdapter(this) // (3)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        adapter.update(buildingService.findAll()) // (4)
    }
    /*
    /** Called when the user taps the button */
   fun openWindow(view: View) {
        // Extract value filled in editext identified with txt_window_name id
        val windowName = findViewById<EditText>(R.id.txt_window_name).text.toString()

        // Do something in response to button
        val intent = Intent(this, WindowActivity::class.java).apply {
            putExtra(WINDOW_NAME_PARAM, windowName)
        }
        startActivity(intent)
    }
    */
    override fun onBuildingSelected(id: Long) {
        val intent = Intent(this, RoomsActivity::class.java).putExtra(BUILDING_NAME_PARAM, id)
        startActivity(intent)
    }


}