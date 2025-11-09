package com.example.doctorbooking.data

import com.example.doctorbooking.R

object SampleData {
    val doctors = listOf(
        Doctor("1", "Dr. Travis Scott", "Cardiologist", 10, R.drawable.doctor1),
        Doctor("2", "Dr. Tillu", "Neurologist", 8,R.drawable.doctor2),
        Doctor("3", "Dr. Hathiram", "Dermatologist", 12,R.drawable.doctor3),
        Doctor("4", "Dr. John Cena", "Pediatrician", 7,R.drawable.doctor4),
        Doctor("5", "Dr. Jai Parashar", "Gynaecologist", 15,R.drawable.doctor5),
        Doctor("6", "Dr. Tejas Janagi", "Oncologist", 1,R.drawable.doctor6),
        Doctor("7", "Dr. Preetam", "Gastrologist", 7,R.drawable.doctor4)


    )




    val appointments = mutableListOf<Appointment>()
}
