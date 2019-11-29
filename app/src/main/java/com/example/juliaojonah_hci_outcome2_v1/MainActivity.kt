package com.example.juliaojonah_hci_outcome2_v1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.security.spec.PSSParameterSpec
import java.text.DateFormat
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateTime = Calendar.getInstance().time

        val dateFormatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(dateTime)

        val dateTextView : TextView = findViewById(R.id.textDate) as TextView
        dateTextView.setText(dateFormatted)
        }


    fun secondActivity(view: View){
        val intent = Intent(this, passwords::class.java)
        startActivity(intent)
    }

    fun randomise(view: View){


        val passwordSet1 = Random.nextInt(0,99)
        val passwordSet2 = Random.nextInt(0,99)
        val passwordSet3 = Random.nextInt(0,99)
        val passwordSet4 = Random.nextInt(0,99)

        val editText1 = findViewById<EditText>(R.id.password1)
        editText1.setText(passwordSet1.toString())

        val editText2 = findViewById<EditText>(R.id.password2)
        editText2.setText(passwordSet2.toString())

        val editText3 = findViewById<EditText>(R.id.password3)
        editText3.setText(passwordSet3.toString())

        val editText4 = findViewById<EditText>(R.id.password4)
        editText4.setText(passwordSet4.toString())

    }



    fun save(view: View){

        val sharedPrefsCounter = getSharedPreferences("passwordAppCounter", Context.MODE_PRIVATE)
        var i = sharedPrefsCounter.getInt("counter", 0)

        if (i == 0){
            val editorCounter = sharedPrefsCounter.edit()

            editorCounter.putInt("counter", 1)
            editorCounter.apply()

            i = 1
        }


        val dateTime = Calendar.getInstance().time

        val dateFormatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(dateTime)


        val description = descriptionName.text.toString().trim()
        val password = password1.text.toString().trim() + "-" + password2.text.toString().trim() + "-" + password3.text.toString().trim() + "-" + password4.text.toString().trim()
        if (description == "" || password == "---"){
            Toast.makeText(this, "Please Enter Proper Value", Toast.LENGTH_LONG).show()
        } else {
            val date = dateTime.toString()

            val data = PasswordCluster(description, password, date)
            val jsonPassword = Gson().toJson(data)


            val sharedPrefsPasswords =
                getSharedPreferences("passwordAppPasswords", Context.MODE_PRIVATE)
            val editorPassword = sharedPrefsPasswords.edit()

            editorPassword.putString("passwordObject" + i, jsonPassword)

            editorPassword.apply()

            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()

            val editorCounter = sharedPrefsCounter.edit()

            editorCounter.putInt("counter", i + 1)
            editorCounter.apply()


        }


    }

}