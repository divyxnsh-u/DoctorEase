package com.example.doctorbooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorbooking.model.Doctor
import com.example.doctorbooking.R

class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val onItemClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private var filteredDoctors =  doctors.toMutableList()

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivDoctor: ImageView = itemView.findViewById(R.id.ivDoctor)
        val tvName: TextView = itemView.findViewById(R.id.tvDoctorName)
        val tvSpecialization: TextView = itemView.findViewById(R.id.tvSpecialization)
        val tvExperience: TextView = itemView.findViewById(R.id.tvExperience)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = filteredDoctors[position]
        holder.tvName.text = doctor.name
        holder.ivDoctor.setImageResource(R.drawable.doctor_placeholder)
        holder.tvSpecialization.text = doctor.specialization
        holder.tvExperience.text = "${doctor.experience} years experience"

        holder.itemView.setOnClickListener {
            onItemClick(doctor)
        }
    }

    override fun getItemCount(): Int = filteredDoctors.size
    fun filter(query: String) {
        val lowerCaseQuery = query.lowercase()
        filteredDoctors = if (lowerCaseQuery.isEmpty()) {
            doctors.toMutableList()
        } else {
            doctors.filter {
                it.specialization.lowercase().contains(lowerCaseQuery)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
    fun updateDoctors(newDoctors: List<Doctor>) {
        filteredDoctors = newDoctors.toMutableList()
        notifyDataSetChanged()
    }
}
