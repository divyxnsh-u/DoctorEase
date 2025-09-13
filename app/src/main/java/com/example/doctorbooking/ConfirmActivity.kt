package com.example.doctorbooking

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class ConfirmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val doctorName = intent.getStringExtra("doctorName") ?: ""
        val department = intent.getStringExtra("department") ?: ""
        val dateMillis = intent.getLongExtra("dateMillis", System.currentTimeMillis())
        val timeString = intent.getStringExtra("timeString") ?: ""
        val patientName = intent.getStringExtra("patientName") ?: ""
        val patientPhone = intent.getStringExtra("patientPhone") ?: ""
        val notes = intent.getStringExtra("notes") ?: ""

        val fmt = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
        val dateStr = fmt.format(Date(dateMillis))

        val tvDetails: TextView = findViewById(R.id.tvDetails)
        tvDetails.text = """Doctor: $doctorName
Dept: $department
Date: $dateStr
Time: $timeString
Patient: $patientName
Phone: $patientPhone
Notes: ${if (notes.isBlank()) "-" else notes}
""".trimIndent()

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener { finishAffinity() }
    }
}
