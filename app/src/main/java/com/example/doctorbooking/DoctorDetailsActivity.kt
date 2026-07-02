package com.example.doctorbooking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore


class DoctorDetailsActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    private lateinit var tvName: TextView
    private lateinit var tvSpecialization: TextView
    private lateinit var tvQualification: TextView
    private lateinit var tvHospital: TextView
    private lateinit var tvExperience: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvFee: TextView
    private lateinit var tvAbout: TextView

    private lateinit var ivDoctor: ImageView
    private lateinit var btnBook: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
        db = FirebaseFirestore.getInstance()

        ivDoctor = findViewById(R.id.ivDoctor)

        tvName = findViewById(R.id.tvName)
        tvSpecialization = findViewById(R.id.tvSpecialization)
        tvQualification = findViewById(R.id.tvQualification)
        tvHospital = findViewById(R.id.tvHospital)
        tvExperience = findViewById(R.id.tvExperience)
        tvRating = findViewById(R.id.tvRating)
        tvFee = findViewById(R.id.tvFee)
        tvAbout = findViewById(R.id.tvAbout)

        btnBook = findViewById(R.id.btnBookAppointment)

        val doctorId = intent.getStringExtra("doctorId")
        if (doctorId == null) {

            finish()
            return

        }

        db.collection("doctors")
            .document(doctorId)
            .get()
            .addOnSuccessListener { document ->

                if (!document.exists()) {

                    finish()
                    return@addOnSuccessListener

                }

                tvName.text = document.getString("name")

                tvSpecialization.text =
                    "Specialization: ${document.getString("specialization")}"

                tvQualification.text =
                    "Qualification: ${document.getString("qualification")}"

                tvHospital.text =
                    "Hospital: ${document.getString("hospital")}"

                tvExperience.text =
                    "Experience: ${document.getLong("experience")} Years"

                tvFee.text =
                    "Consultation Fee: ₹${document.getLong("consultationFee")}"

                tvRating.text =
                    "⭐ ${document.getDouble("rating")}"

                tvAbout.text =
                    document.getString("about")

                ivDoctor.setImageResource(R.drawable.doctor_placeholder)

            }
        btnBook.setOnClickListener {

            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("doctorId", doctorId)

            startActivity(intent)

        }
    }
}