package com.example.doctorbooking

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.example.doctorbooking.model.Doctor
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.example.doctorbooking.databinding.ActivityBookingBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.doctorbooking.model.Appointment


class BookingActivity : AppCompatActivity() {

    private var selectedButton: Button? = null
    private var selectedDate = ""
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityBookingBinding
    private var selectedSlot = ""
    private lateinit var doctor: Doctor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnConfirm.isEnabled = false

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

        auth = FirebaseAuth.getInstance()

        val doctorId = intent.getStringExtra("doctorId") ?: return

        FirebaseFirestore.getInstance()
            .collection("doctors")
            .document(doctorId)
            .get()
            .addOnSuccessListener { document ->
                doctor = document.toObject(Doctor::class.java)!!
                doctor.id = document.id
                binding.tvDoctorName.text = doctor.name
                binding.tvDoctorSpecialization.text = doctor.specialization
                binding.tvDoctorExperience.text =
                    "Experience: ${doctor.experience} Years"
                binding.tvDoctorFee.text =
                    "Consultation Fee: ₹${doctor.consultationFee}"
                binding.tvDoctorRating.text = "⭐ ${doctor.rating}"

                binding.tvDoctorHospital.text = "🏥 ${doctor.hospital}"
                binding.btnConfirm.isEnabled = true

            }

//         // Set the default date to today
        binding.btnSelectDate.setOnClickListener {
            val c = Calendar.getInstance()
            val dpd = DatePickerDialog(
                this,
                { _, y, m, d ->
                    selectedDate = String.format(
                        "%04d-%02d-%02d",
                        y,
                        m + 1,
                        d
                    )
                    binding.btnSelectDate.text =
                        String.format(
                            "📅 %02d/%02d/%04d",
                            d,
                            m + 1,
                            y
                        )
                    loadBookedSlots(selectedDate)
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }

        binding.btnConfirm.setOnClickListener {
            val name = binding.etPatientName.text.toString().trim()
            val phone = binding.etPatientPhone.text.toString().trim()

            if (selectedSlot.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please select a time slot",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter name and phone number!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (phone.length != 10) {
                Toast.makeText(this, "Please enter a valid 10-digit phone number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else {
                val currentUser = auth.currentUser
                if (currentUser == null) {
                    Toast.makeText(
                        this,
                        "Please login again",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                val appointment = Appointment(
                    userId = currentUser.uid,
                    doctorId = doctor.id,
                    doctorName = doctor.name,
                    patientName = name,
                    patientPhone = phone,
                    relation = "Self",
                    appointmentDate = selectedDate,
                    appointmentTime = selectedSlot
                )
                FirebaseFirestore.getInstance()
                    .collection("appointments")
                    .add(appointment)
                    .addOnSuccessListener { documentReference ->

                        documentReference.update("id", documentReference.id)

                        Toast.makeText(
                            this,
                            "Appointment Booked Successfully!",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Booking Failed!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }
    private fun generateSlots() {
        binding.layoutSlots.removeAllViews()
        val slots = SlotGenerator.generateSlots(
            doctor.startTime,
            doctor.endTime,
            doctor.slotDuration
        )
        for (slot in slots) {
            val button = Button(this)
            button.text = slot
            button.setOnClickListener {
                selectedSlot = slot

                Toast.makeText(
                    this,
                    "Selected: $slot",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.layoutSlots.addView(button)
        }
    }
    private fun loadBookedSlots(date: String) {

        Toast.makeText(this, "Loading booked slots...", Toast.LENGTH_SHORT).show()

        FirebaseFirestore.getInstance()
            .collection("appointments")
            .whereEqualTo("doctorId", doctor.id)
            .whereEqualTo("appointmentDate", date)
            .get()
            .addOnSuccessListener { result ->

                Toast.makeText(
                    this,
                    "Appointments found: ${result.size()}",
                    Toast.LENGTH_SHORT
                ).show()

                val bookedSlots = mutableListOf<String>()
                for (document in result) {
                    val status =
                        document.getString("status") ?: "Upcoming"
                    if (status != "Cancelled") {
                        val time =
                            document.getString("appointmentTime") ?: ""
                        bookedSlots.add(time)
                    }
                }
                showSlots(bookedSlots)
            }
    }
    private fun showSlots(bookedSlots: List<String>) {

        binding.layoutSlots.removeAllViews()
        val slots = SlotGenerator.generateSlots(
            doctor.startTime,
            doctor.endTime,
            doctor.slotDuration
        )

        for (slot in slots) {
            val button = Button(this)
            button.text = slot
            button.layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            }

            button.setPadding(20, 20, 20, 20)
            if (bookedSlots.contains(slot)) {
                button.isEnabled = false
                button.setBackgroundColor(
                    android.graphics.Color.parseColor("#F44336")
                )
                button.setTextColor(android.graphics.Color.WHITE)
            } else {
                button.setBackgroundColor(
                    android.graphics.Color.parseColor("#4CAF50")
                )
                button.setTextColor(android.graphics.Color.WHITE)
                button.setOnClickListener {
                    // Restore the previously selected button
                    selectedButton?.setBackgroundColor(
                        android.graphics.Color.parseColor("#4CAF50")
                    )
                    selectedButton?.setTextColor(android.graphics.Color.WHITE)
                    selectedButton = button
                    selectedSlot = slot
                    button.setBackgroundColor(
                        android.graphics.Color.parseColor("#9C27B0")
                    )
                    button.setTextColor(android.graphics.Color.WHITE)

                }
            }
            binding.layoutSlots.addView(button)
        }
    }

}
