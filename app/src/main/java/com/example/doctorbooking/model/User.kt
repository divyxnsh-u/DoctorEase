package com.example.doctorbooking.model

data class User(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val role: String = "patient"
)