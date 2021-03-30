package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast

class TempActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        val acceptButton: Button = findViewById<Button>(R.id.acceptButton2)

        acceptButton.setOnClickListener { v: View ->
            val arrivalStation: String = findViewById<EditText>(R.id.arrivalStationInput2).text.toString()
            val departureStation: String = findViewById<EditText>(R.id.departureStationInput2).text.toString()
            val time: TimePicker = findViewById<TimePicker>(R.id.timeInput)
            val timeString: String =  "${time.hour}:${time.minute}"
            Toast.makeText(this, "$departureStation -> $arrivalStation $timeString", Toast.LENGTH_LONG).show()
        }

        /*acceptButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val temp: EditText = findViewById<EditText>(R.id.arrivalStationInput);
                val someText: String = temp.text.toString();
                Toast.makeText(this@MainActivity, "$someText",Toast.LENGTH_SHORT).show()
            }
        })*/
    }
}