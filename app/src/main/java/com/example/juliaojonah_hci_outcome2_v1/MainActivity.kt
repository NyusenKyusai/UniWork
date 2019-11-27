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
import kotlinx.android.synthetic.main.activity_main.*
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

        savedPasswords.setOnClickListener{
            val intent = Intent(this, passwords::class.java)
            startActivity(intent)
        }

        val saveButtonClick = findViewById(R.id.save) as Button



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


        val sharedPrefs = getSharedPreferences("passwordAppFile", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        editor.putString("description", descriptionName.text.toString())
        editor.putString("password1", password1.text.toString())
        editor.putString("password2", password2.text.toString())
        editor.putString("password3", password3.text.toString())
        editor.putString("password4", password4.text.toString())

        editor.apply()
        Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()
    }

}