package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

/*Вікно вибору поїзду містить: два текстові поля (від і до),
 групу опцій (час відправлення) та кнопку «ОК». Вивести інформацію щодо вибору*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val acceptButton: Button = findViewById<Button>(R.id.acceptButton)

        acceptButton.setOnClickListener { v:View ->
            val departureStation: String = findViewById<TextView>(R.id.departureStationInput).text.toString()
            if (departureStation == ""){
                Toast.makeText(this, "Enter departure station",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val arrivalStation: String = findViewById<EditText>(R.id.arrivalStationInput).text.toString()
            if (arrivalStation == ""){
                Toast.makeText(this, "Enter arrival station",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val radioGroup: RadioGroup = findViewById<RadioGroup>(R.id.radioTime)

            when(val checkedRadioButtonId = radioGroup.checkedRadioButtonId){
                -1 -> {
                    Toast.makeText(this, "Choose time",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
                    Toast.makeText(this, "Accept order",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TicketActivity::class.java).apply {
                        putExtra("departureStation", departureStation)
                        putExtra("arrivalStation", arrivalStation)
                        putExtra("time", selectedRadioButton.text)
                    }
                    startActivity(intent)
                }
            }
        }

        val magicButton: Button = findViewById<Button>(R.id.magicButton)

        magicButton.setOnClickListener { v:View ->
            val intent = Intent(this, TempActivity::class.java)
            startActivity(intent)
        }

    }
}
