package com.example.doctorbooking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorbooking.model.Doctor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.example.doctorbooking.databinding.ActivityMainBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class DoctorListActivity : AppCompatActivity() {
    private lateinit var adapter: DoctorAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore
    private val doctorList = ArrayList<Doctor>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                0,
                systemBars.top,
                0,
                0
            )

            insets
        }

        db = FirebaseFirestore.getInstance()


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)



        adapter = DoctorAdapter(doctorList) { doctor ->

            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("doctorId", doctor.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        loadDoctors()
    }

    private fun loadDoctors() {

        db.collection("doctors")
            .orderBy("name")
            .get()
            .addOnSuccessListener { result ->

                doctorList.clear()

                for (document in result) {

                    val doctor = document.toObject(Doctor::class.java)

                    doctor.id = document.id

                    doctorList.add(doctor)

                }

                adapter.updateDoctors(doctorList)

            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "Failed to load doctors",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        searchView?.queryHint = "Search by department..."
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_my_appointments -> {
                startActivity(
                    Intent(this, MyAppointmentsActivity::class.java)
                )
                true
            }
            R.id.action_logout -> {
                showLogoutConfirmationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { _, _ -> logoutUser() }
            .setNegativeButton("Cancel", null)
            .show()
    }


    private fun logoutUser() {

        FirebaseAuth.getInstance().signOut()

        Toast.makeText(
            this,
            "Logged out successfully!",
            Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(this, DoctorDetailsActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
        finish()
    }
}
