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
import com.example.doctorbooking.data.SampleData

class DoctorListActivity : AppCompatActivity() {
    private lateinit var adapter: DoctorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DoctorAdapter(SampleData.doctors) { doctor ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("doctorName", doctor.name)
            intent.putExtra("specialization", doctor.specialization)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
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
        val prefs = getSharedPreferences("DoctorAppPrefs", MODE_PRIVATE)
        prefs.edit().putBoolean("isLoggedIn", false).apply()

        Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
