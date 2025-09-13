package com.example.doctorbooking.data

object SampleData {
    val doctors = listOf(
        Doctor("1", "Dr. Travis Scott", "Cardiologist", 10),
        Doctor("2", "Dr. Tillu", "Neurologist", 8),
        Doctor("3", "Dr. Hathiram", "Dermatologist", 12),
        Doctor("4", "Dr. John Cena", "Pediatrician", 7)

    )




    val appointments = mutableListOf<Appointment>()
}
