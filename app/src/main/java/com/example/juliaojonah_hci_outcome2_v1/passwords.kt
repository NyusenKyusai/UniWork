package com.example.juliaojonah_hci_outcome2_v1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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

        createPassword2.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val sharedPrefsCounter = getSharedPreferences("passwordAppCounter", Context.MODE_PRIVATE)
        val counter = sharedPrefsCounter.getInt("counter", 0)

        if (counter > 0) {

            val sharedPrefsCounter = getSharedPreferences("passwordAppPasswords", Context.MODE_PRIVATE)

            for (i in 1..counter) {
                val objectString = sharedPrefsCounter.getString("passwordObject$i", "")

                val personObj = Gson().fromJson<PasswordCluster>(objectString, PasswordCluster::class.java!!)


                val editText = findViewById<TextView>(R.id.savedPasswords)
                editText.setText(personObj.toString())
            }
        }
    }
}
