package com.example.doctorbooking.model



data class Doctor(
    var id: String = "",
    val name: String = "",
    val specialization: String = "",
    val experience: Long = 0,
    val hospital: String = "",
    val consultationFee: Long = 0,
    val rating: Double = 0.0,
    val qualification: String = "",
    val about: String = "",
    val available: Boolean = true,
    val imageUrl: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val slotDuration: Long = 30,
    val workingDays: List<String> = emptyList()
)