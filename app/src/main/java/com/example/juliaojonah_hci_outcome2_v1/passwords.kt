package com.example.juliaojonah_hci_outcome2_v1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*

class passwords : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passwords)

        val dateTime = Calendar.getInstance().time

        val dateFormatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(dateTime)

        val dateTextView : TextView = findViewById(R.id.textDate2) as TextView
        dateTextView.setText(dateFormatted)

        val sharedPrefsCounter = getSharedPreferences("passwordAppCounter", Context.MODE_PRIVATE)
        val counter = sharedPrefsCounter.getInt("counter", 0)

        val recyclerView = findViewById(R.id.savedPasswords) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val adapter = CustomAdapter(passwords)

        recyclerView.adapter = adapter


        if (counter > 0) {

            val sharedPrefsCounter = getSharedPreferences("passwordAppPasswords", Context.MODE_PRIVATE)

            for (i in 1..counter) {

                val objectString = sharedPrefsCounter.getString("passwordObject$i", "")

                val passwordObject = Gson().fromJson<PasswordCluster>(objectString, PasswordCluster::class.java!!)




            }
        }


    }

    fun firstActivity(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
