package com.example.juliaojonah_hci_outcome2_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
    }
}
