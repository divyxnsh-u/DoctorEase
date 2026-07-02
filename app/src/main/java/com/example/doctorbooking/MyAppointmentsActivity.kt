package com.example.doctorbooking

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorbooking.databinding.ActivityMyAppointmentsBinding
import com.example.doctorbooking.model.Appointment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyAppointmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyAppointmentsBinding

    private val appointmentList = mutableListOf<Appointment>()

    private lateinit var adapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyAppointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                0,
                systemBars.top,
                0,
                0
            )

            insets
        }

        adapter = AppointmentAdapter(appointmentList)

        binding.recyclerAppointments.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerAppointments.adapter = adapter

        loadAppointments()
    }

    private fun loadAppointments() {

        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        FirebaseFirestore.getInstance()
            .collection("appointments")
            .whereEqualTo("userId", uid)
            .get()
            .addOnSuccessListener { result ->

                appointmentList.clear()

                for (document in result) {

                    val appointment =
                        document.toObject(Appointment::class.java)

                    appointment.id = document.id

                    appointmentList.add(appointment)
                }

                adapter.notifyDataSetChanged()
            }
    }
}