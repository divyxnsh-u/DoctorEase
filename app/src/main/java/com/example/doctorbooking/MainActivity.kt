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


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val doctors = listOf(
            Doctor("1", "Dr. Travis Scott", "Cardiologist", 10, R.drawable.doctor1),
            Doctor("2", "Dr. Tillu", "Neurologist", 8,R.drawable.doctor2),
            Doctor("3", "Dr. Hathiram", "Dermatologist", 12,R.drawable.doctor3),
            Doctor("4", "Dr. John Cena", "Pediatrician", 7,R.drawable.doctor4),
            Doctor("5", "Dr. Jai Parashar", "Gynaecologist", 15,R.drawable.doctor5),
            Doctor("6", "Dr. Tejas Janagi", "Oncologist", 1,R.drawable.doctor6),
            Doctor("7", "Dr. Preetam", "Gastrologist", 7,R.drawable.doctor4),

        )



        val adapter = DoctorAdapter(doctors) { doctor ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("doctorName", doctor.name)
            intent.putExtra("doctorSpecialization", doctor.specialization)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
