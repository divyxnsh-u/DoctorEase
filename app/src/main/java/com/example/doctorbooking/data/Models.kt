package com.example.doctorbooking.data

data class Doctor(
    val id: String,
    val name: String,
    val specialization: String,
    val experience: Int
)

data class Appointment(
    val doctor: Doctor,
    val dateMillis: Long,
    val timeString: String,
    val patientName: String,
    val patientPhone: String,
    val notes: String?
)
