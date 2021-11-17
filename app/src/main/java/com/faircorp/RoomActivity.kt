package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.model.OnRoomSelectedListener
import com.faircorp.model.adapters.RoomsAdapter
import com.faircorp.model.services.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : BasicActivity(){
    var roomId : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        //we obtain the id of the room selected
        val id = intent.getLongExtra(ROOM_NAME_PARAM,0)
        roomId=id
        println("room id : "+id)
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().roomApiService.findById(id).execute()} // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        val room = it.body()
                        println("Rooms found:")
                        println(it.body())

                        if (room != null){
                            findViewById<TextView>(R.id.txt_room_name).text = room.name
                            println("temperature"+room.current_temperature)
                            findViewById<TextView>(R.id.room_curent_temp).text = room.current_temperature.toString()
                            findViewById<TextView>(R.id.room_target_temp).text = room.target_temperature.toString()

                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on room loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    fun openWindow(view: View) {
        //Do something in response to button
        val intent = Intent(this, WindowsActivity::class.java).putExtra("roomId",roomId)
        startActivity(intent)
    }
}