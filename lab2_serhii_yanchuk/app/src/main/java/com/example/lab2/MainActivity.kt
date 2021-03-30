package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), InputFragment.OnAcceptedOrderListener {
    private val keyDepartureStation = "departureStation"
    private val keyArrivalStation = "arrivalStation"
    private val keyTime = "time"
    private var departureStationSave: String = ""
    private var arrivalStationSave: String = ""
    private var timeSave: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            departureStationSave = savedInstanceState.getString(keyDepartureStation, "")
            arrivalStationSave = savedInstanceState.getString(keyArrivalStation, "")
            timeSave = savedInstanceState.getString(keyTime, "")
        }

        val fragmentManager = supportFragmentManager
        val outputFragment = fragmentManager.findFragmentById(R.id.outputFragment) as OutputFragment?

        if (outputFragment != null && outputFragment?.isInLayout)
            outputFragment?.setTicketInfo(departureStationSave, arrivalStationSave, timeSave)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(keyDepartureStation, departureStationSave)
        outState.putString(keyArrivalStation, arrivalStationSave)
        outState.putString(keyTime, timeSave)
    }

    override fun onOrderAccepted(departureStation: String, arrivalStation: String, time: String) {
        val fragmentManager = supportFragmentManager
        departureStationSave = departureStation
        arrivalStationSave = arrivalStation
        timeSave = time
        val outputFragment = fragmentManager.findFragmentById(R.id.outputFragment) as OutputFragment?

        if (outputFragment == null || !outputFragment?.isVisible) {
            val intent = Intent(this, TicketActivity::class.java).apply {
                putExtra("departureStation", departureStation)
                putExtra("arrivalStation", arrivalStation)
                putExtra("time", time)
            }
            startActivity(intent)
        } else {
            outputFragment?.setTicketInfo(departureStation, arrivalStation, time)
        }
    }
}