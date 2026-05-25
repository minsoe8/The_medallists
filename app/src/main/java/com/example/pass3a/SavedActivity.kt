package com.example.pass3a

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SavedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        val textView = findViewById<TextView>(R.id.savedText)

        val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val country = prefs.getString("country", "None")
        val code = prefs.getString("code", "")
        val gold = prefs.getInt("gold", 0)
        val silver = prefs.getInt("silver", 0)
        val bronze = prefs.getInt("bronze", 0)

        textView.text = """
            Last Selected Country
            
            Country: $country
            Code: $code
            
            Gold: $gold
            Silver: $silver
            Bronze: $bronze
        """.trimIndent()
    }
}