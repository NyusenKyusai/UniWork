package com.example.juliaojonah_hci_outcome2_v1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mrtayyab.sqlitedbkotlin.DatabaseHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.security.spec.PSSParameterSpec
import java.text.DateFormat
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var myDb: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateTime = Calendar.getInstance().time

        val dateFormatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(dateTime)

        val dateTextView : TextView = findViewById(R.id.textDate) as TextView
        dateTextView.setText(dateFormatted)

        myDb = DatabaseHelper(this)
        }


    fun secondActivity(view: View){
        val intent = Intent(this, passwords::class.java)
        startActivity(intent)
    }

    fun randomise(view: View) {


        var passwordSet1 = Random.nextInt(0, 99)
        var passwordSet2 = Random.nextInt(0, 99)
        var passwordSet3 = Random.nextInt(0, 99)
        var passwordSet4 = Random.nextInt(0, 99)

        var passwordSet1T = ""
        var passwordSet2T = ""
        var passwordSet3T = ""
        var passwordSet4T = ""
        var password1 = true
        var password2 = true
        var password3 = true
        var password4 = true

        if (passwordSet1 < 10) {
            passwordSet1T = "0" + passwordSet1
            password1 = false
        }

        if (passwordSet2 < 10) {
            passwordSet2T = "0" + passwordSet2
            password2 = false
        }

        if (passwordSet3 < 10) {
            passwordSet3T = "0" + passwordSet3
            password3 = false
        }

        if (passwordSet4 < 10) {
            passwordSet4T = "0" + passwordSet4
            password4 = false
        }

        val editText1 = findViewById<EditText>(R.id.password1)
        if (password1 == false) {
            editText1.setText(passwordSet1T.toString())
        } else {
            editText1.setText(passwordSet1.toString())
        }

        val editText2 = findViewById<EditText>(R.id.password2)
        if (password2 == false) {
            editText2.setText(passwordSet2T.toString())
        } else {
            editText2.setText(passwordSet2.toString())
        }

        val editText3 = findViewById<EditText>(R.id.password3)
        if (password3 == false) {
            editText3.setText(passwordSet3T.toString())
        } else {
            editText3.setText(passwordSet3.toString())
        }

        val editText4 = findViewById<EditText>(R.id.password4)
        if (password4 == false) {
            editText4.setText(passwordSet4T.toString())
        } else {
            editText4.setText(passwordSet4.toString())
        }

    }



    fun save(view: View){
        var correct = true

        val description = descriptionName.text.toString().trim()
        val password = password1.text.toString().trim() + "-" + password2.text.toString().trim() + "-" + password3.text.toString().trim() + "-" + password4.text.toString().trim()
        val dateTime = Calendar.getInstance().time
        val dateFormatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(dateTime)

        if (TextUtils.isEmpty(description)){
            descriptionName.error = "Enter description"
            correct = false
        }

        if (password == "---"){
            password1.error = "Enter password"
            password2.error = "Enter password"
            password3.error = "Enter password"
            password4.error = "Enter password"
            correct = false
        }


        var isInserted = myDb.insertData(description, password, dateFormatted)


        if (isInserted == true && correct == true){
            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Data Not Saved", Toast.LENGTH_LONG).show()
        }




    }

}