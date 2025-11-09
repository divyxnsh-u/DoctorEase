package com.example.doctorbooking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val doctorName = intent.getStringExtra("doctorName")
        val doctorSpecialization = intent.getStringExtra("doctorSpecialization")

        val tvBookingInfo = findViewById<TextView>(R.id.tvBookingInfo)
        val btnConfirmBooking = findViewById<Button>(R.id.btnConfirm)
        val btnSelectDate = findViewById<Button>(R.id.btnSelectDate)
        val btnSelectTime = findViewById<Button>(R.id.btnSelectTime)
        val etPatientName = findViewById<EditText>(R.id.etPatientName)
        val etPatientPhone = findViewById<EditText>(R.id.etPatientPhone)

        tvBookingInfo.text = "Booking appointment with:\n$doctorName\n($doctorSpecialization)"

        btnSelectDate.setOnClickListener {
            val c = Calendar.getInstance()
            val dpd = DatePickerDialog(
                this,
                { _, y, m, d -> btnSelectDate.text = "📅 $d/${m + 1}/$y" },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }

        btnSelectTime.setOnClickListener {
            val c = Calendar.getInstance()
            val tpd = TimePickerDialog(
                this,
                { _, h, m -> btnSelectTime.text = "⏰ $h:$m" },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                true
            )
            tpd.show()
        }


        btnConfirmBooking.setOnClickListener {
            val name = etPatientName.text.toString().trim()
            val phone = etPatientPhone.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please enter name and phone number!", Toast.LENGTH_SHORT).show()
            }
            if (phone.length != 10) {
                Toast.makeText(this, "Please enter a valid 10-digit phone number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else {
                tvBookingInfo.text = "✅ Appointment confirmed with $doctorName!\nPatient: $name\nPhone: $phone"
            }
        }
    }
}
