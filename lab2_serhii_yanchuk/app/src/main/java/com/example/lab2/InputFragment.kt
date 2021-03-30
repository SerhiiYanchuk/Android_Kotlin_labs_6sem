package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class InputFragment: Fragment(), View.OnClickListener {
    interface OnAcceptedOrderListener {
        fun onOrderAccepted(departureStation: String, arrivalStation: String, time: String)
    }

    private lateinit var viewOfLayout: View
    private lateinit var departureView: EditText
    private lateinit var arrivalView: EditText
    private lateinit var timeView: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOfLayout = inflater.inflate(R.layout.fragment_input, container, false)
        val acceptButton: Button = viewOfLayout.findViewById(R.id.acceptButton)
        acceptButton.setOnClickListener(this)
        departureView = viewOfLayout.findViewById(R.id.departureStationInput)
        arrivalView = viewOfLayout.findViewById(R.id.arrivalStationInput)
        timeView = viewOfLayout.findViewById(R.id.radioTime)
        return viewOfLayout
    }

    override fun onClick(v: View?) {
        if (departureView.text.toString() == ""){
            Toast.makeText(activity, "Enter departure station", Toast.LENGTH_SHORT).show()
            return
        }
        if (arrivalView.text.toString() == ""){
            Toast.makeText(activity, "Enter arrival station", Toast.LENGTH_SHORT).show()
            return
        }

        when(val checkedRadioButtonId = timeView.checkedRadioButtonId){
            -1 -> {
                Toast.makeText(activity, "Choose time", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val selectedRadioButton = viewOfLayout.findViewById<RadioButton>(checkedRadioButtonId)
                Toast.makeText(activity, "Accept order", Toast.LENGTH_SHORT).show()
                val listener = activity as OnAcceptedOrderListener?
                listener?.onOrderAccepted(departureView.text.toString(), arrivalView.text.toString(), selectedRadioButton.text.toString())
            }
        }
    }
}
