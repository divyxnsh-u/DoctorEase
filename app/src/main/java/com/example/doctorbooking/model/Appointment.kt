package com.example.doctorbooking.model

data class Appointment(
    var id: String = "",
    var userId: String = "",
    var doctorId: String = "",
    var doctorName: String = "",
    var patientName: String = "",
    var patientPhone: String = "",
    var relation: String = "",
    var appointmentDate: String = "",
    var appointmentTime: String = "",
    var status: String = "Upcoming",
    var createdAt: Long = System.currentTimeMillis()
)