package com.example.pass3a

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.content.SharedPreferences
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var prefs: SharedPreferences
    private val medalList = mutableListOf<Medal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        readCSV()

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MedalAdapter(medalList) {

            prefs.edit()
                .putString("country", it.country)
                .putString("code", it.code)
                .putInt("gold", it.gold)
                .putInt("silver", it.silver)
                .putInt("bronze", it.bronze)
                .apply()
        }
    }

    private fun readCSV() {

        val inputStream = assets.open("medallists.csv")

        val reader = BufferedReader(InputStreamReader(inputStream))

        reader.readLine()

        reader.forEachLine {

            val tokens = it.split(",")

            medalList.add(
                Medal(
                    tokens[0],
                    tokens[1],
                    tokens[2].toInt(),
                    tokens[3].toInt(),
                    tokens[4].toInt(),
                    tokens[5].toInt()
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.savedMenu) {

            startActivity(Intent(this, SavedActivity::class.java))
        }

        return true
    }
}