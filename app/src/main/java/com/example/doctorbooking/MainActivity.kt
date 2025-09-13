package com.example.doctorbooking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import com.example.doctorbooking.data.Doctor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar setup
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // RecyclerView setup
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val doctors = listOf(
            Doctor("1", "Dr. Travis Scott", "Cardiologist", 10),
            Doctor("2", "Dr. Tillu", "Neurologist", 8),
            Doctor("3", "Dr. Hathiram", "Dermatologist", 12),
            Doctor("4", "Dr. John Cena", "Pediatrician", 7)

        )


        // Adapter setup
        val adapter = DoctorAdapter(doctors) { doctor ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("doctorName", doctor.name)
            intent.putExtra("doctorSpecialization", doctor.specialization)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
