package com.example.juliaojonah_hci_outcome2_v1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mrtayyab.sqlitedbkotlin.DatabaseHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class passwords : AppCompatActivity() {

    lateinit var myDb: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passwords)



        val dateTime = Calendar.getInstance().time

        val dateFormatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(dateTime)

        val dateTextView : TextView = findViewById(R.id.textDate2) as TextView
        dateTextView.setText(dateFormatted)

        val recyclerView = findViewById(R.id.savedPasswords) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val counter = myDb.getTableCount()

        val passwords = ArrayList<PasswordCluster>()

        if (counter > 0) {
            for (i in 1..counter) {
                var id = i
                var description = myDb.getData(id.toString(), "COL_2")
                var password = myDb.getData(id.toString(), "COL_3")
                var dateTime = myDb.getData(id.toString(), "COL_4")

                passwords.add(PasswordCluster(description.toString(), password.toString(), dateTime.toString()))
            }
        }

/*
        passwords.add(PasswordCluster("Amazon", "56,78,90,12", "Friday"))
        passwords.add(PasswordCluster("Amazon", "56,78,90,12", "Friday"))
        passwords.add(PasswordCluster("Amazon", "56,78,90,12", "Friday"))
        passwords.add(PasswordCluster("Amazon", "56,78,90,12", "Friday"))
        passwords.add(PasswordCluster("Amazon", "56,78,90,12", "Friday"))
*/
        val adapter = CustomAdapter(passwords)

        recyclerView.adapter = adapter
    }

    fun firstActivity(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
