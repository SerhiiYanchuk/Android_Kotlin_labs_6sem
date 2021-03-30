package com.example.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class OutputFragment: Fragment() {
    private lateinit var departureView: TextView
    private lateinit var arrivalView: TextView
    private lateinit var timeView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_output, container, false)
        departureView = rootView.findViewById(R.id.textDepartureStation)
        arrivalView = rootView.findViewById(R.id.textArrivalStation)
        timeView = rootView.findViewById(R.id.textTime)
        return rootView
    }

    fun setTicketInfo(departureStation: String, arrivalStation: String, time: String){
        departureView.text = departureStation
        arrivalView.text = arrivalStation
        timeView.text = time
    }
}