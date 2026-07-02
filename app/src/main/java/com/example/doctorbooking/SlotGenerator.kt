package com.example.doctorbooking

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object SlotGenerator {

    fun generateSlots(
        startTime: String,
        endTime: String,
        slotDuration: Long
    ): List<String> {

        val slots = mutableListOf<String>()

        val format = SimpleDateFormat("HH:mm", Locale.getDefault())

        val start = Calendar.getInstance()
        start.time = format.parse(startTime)!!

        val end = Calendar.getInstance()
        end.time = format.parse(endTime)!!

        while (start.before(end)) {

            slots.add(format.format(start.time))

            start.add(Calendar.MINUTE, slotDuration.toInt())
        }

        return slots
    }
}