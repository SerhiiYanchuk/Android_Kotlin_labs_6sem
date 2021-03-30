package com.example.lab3

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var databaseHelper: TicketDbHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val departureStation: EditText = findViewById<EditText>(R.id.departureStationInput)
        val arrivalStation: EditText = findViewById<EditText>(R.id.arrivalStationInput)
        val radioGroup: RadioGroup = findViewById<RadioGroup>(R.id.radioTime)
        databaseHelper = TicketDbHelper(applicationContext)
        val acceptButton: Button = findViewById<Button>(R.id.acceptButton)

        acceptButton.setOnClickListener { v: View ->
            if (departureStation.text.toString() == ""){
                Toast.makeText(this, "Enter departure station", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (arrivalStation.text.toString() == ""){
                Toast.makeText(this, "Enter arrival station", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            when(val checkedRadioButtonId = radioGroup.checkedRadioButtonId){
                -1 -> {
                    Toast.makeText(this, "Choose time", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
                    val db: SQLiteDatabase = databaseHelper!!.writableDatabase

                    val cv = ContentValues()
                    cv.put(TicketDbHelper.COLUMN_DEPARTURE, departureStation.text.toString())
                    cv.put(TicketDbHelper.COLUMN_ARRIVAL, arrivalStation.text.toString())
                    cv.put(TicketDbHelper.COLUMN_TIME, selectedRadioButton.text.toString())
                    db.insert(TicketDbHelper.TABLE, null, cv)
                    Toast.makeText(this, "Accept order", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val magicButton: Button = findViewById<Button>(R.id.magicButton)

        magicButton.setOnClickListener { v:View ->
            val db: SQLiteDatabase = databaseHelper!!.readableDatabase
            val ticketCursor: Cursor = db.rawQuery("select * from " + TicketDbHelper.TABLE + " limit 1", null)
            if (ticketCursor.count == 0)
            {
                Toast.makeText(this, "The database is empty", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this, TicketActivity::class.java)
                startActivity(intent)
            }

        }
    }
}