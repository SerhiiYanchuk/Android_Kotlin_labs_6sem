package com.example.lab2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        val i = intent
        if (i != null){
            val fragmentManager = supportFragmentManager
            val outputFragment = fragmentManager.findFragmentById(R.id.outputFragment2) as OutputFragment?
            val departureStation: String = i.getCharSequenceExtra("departureStation").toString();
            val arrivalStation: String = i.getCharSequenceExtra("arrivalStation").toString();
            val time: String = i.getCharSequenceExtra("time").toString();
            outputFragment?.setTicketInfo(departureStation, arrivalStation, time)
        }
    }
}