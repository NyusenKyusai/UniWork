package com.example.juliaojonah_hci_outcome2_v1

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        myDb = DatabaseHelper(this)

        val dateTime = Calendar.getInstance().time

        val dateFormatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(dateTime)

        val dateTextView : TextView = findViewById(R.id.textDate2) as TextView
        dateTextView.setText(dateFormatted)

        val recyclerView = findViewById(R.id.savedPasswords) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        addSomeDataIfNone()

        val passwords = myDb.getListOfAllPasswordClusters()

        for (p: PasswordCluster in passwords) {
            Log.d("PASSWORDCLUSTER","ID=${p.id} Description=${p.description} password=${p.passwordCluster} datetime=${p.dateTime}")
        }

        val adapter = CustomAdapter(passwords)

        recyclerView.adapter = adapter
    }

    fun firstActivity(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun addSomeDataIfNone() {
        if (DatabaseUtils.queryNumEntries(myDb.writableDatabase,DatabaseHelper.TABLE_NAME) > 0) return
        myDb.insertData("Test1","password1","2019-12-01")
        myDb.insertData("Test2","password2","2019-12-02")
        myDb.insertData("Test3","password3","2019-12-03")
    }
}
