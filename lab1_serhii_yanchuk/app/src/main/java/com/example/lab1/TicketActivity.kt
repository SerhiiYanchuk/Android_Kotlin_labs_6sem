package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        
        val i = intent
        if (i != null){
            findViewById<TextView>(R.id.textDepartureStation).text = i.getCharSequenceExtra("departureStation")
            findViewById<TextView>(R.id.textArrivalStation).text = i.getCharSequenceExtra("arrivalStation")
            findViewById<TextView>(R.id.textTime).text = i.getCharSequenceExtra("time")
        }
    }
}