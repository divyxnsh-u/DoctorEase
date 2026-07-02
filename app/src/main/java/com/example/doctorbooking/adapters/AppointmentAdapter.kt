package com.example.doctorbooking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorbooking.databinding.ItemAppointmentBinding
import com.example.doctorbooking.model.Appointment
import com.google.firebase.firestore.FirebaseFirestore
import androidx.appcompat.app.AlertDialog
import android.graphics.Color
import android.widget.Toast

class AppointmentAdapter(
    private val appointments: MutableList<Appointment>
) : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemAppointmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount() = appointments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val appointment = appointments[position]

        holder.binding.tvDoctorName.text = appointment.doctorName
        holder.binding.tvPatient.text = "Patient: ${appointment.patientName}"
        holder.binding.tvDate.text = "Date: ${appointment.appointmentDate}"
        holder.binding.tvTime.text = "Time: ${appointment.appointmentTime}"
        holder.binding.tvStatus.text = appointment.status
        if (appointment.status == "Cancelled") {

            holder.binding.tvStatus.setTextColor(Color.RED)
            holder.binding.btnCancel.isEnabled = false
            holder.binding.btnCancel.text = "Cancelled"

        } else {

            holder.binding.tvStatus.setTextColor(Color.parseColor("#2E7D32"))
            holder.binding.btnCancel.isEnabled = true
            holder.binding.btnCancel.text = "Cancel Appointment"
        }

        holder.binding.btnCancel.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Cancel Appointment")
                .setMessage("Are you sure you want to cancel this appointment?")
                .setPositiveButton("Yes") { _, _ ->

                    FirebaseFirestore.getInstance()
                        .collection("appointments")
                        .document(appointment.id)
                        .update("status", "Cancelled")
                        .addOnSuccessListener {

                            appointment.status = "Cancelled"
                            notifyItemChanged(position)

                            Toast.makeText(
                                holder.itemView.context,
                                "Appointment Cancelled",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}