package com.example.doctorbooking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorbooking.data.SampleData

class DoctorListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // ✅ This is your doctor list UI

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = DoctorAdapter(SampleData.doctors) { doctor ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("doctorName", doctor.name)
            intent.putExtra("specialization", doctor.specialization)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
