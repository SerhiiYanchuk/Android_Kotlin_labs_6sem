package com.example.lab3


import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TicketActivity : AppCompatActivity() {
    private lateinit var ticketList: ListView
    private lateinit var header: TextView
    private lateinit var databaseHelper: TicketDbHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var ticketCursor: Cursor
    private lateinit var ticketAdapter: TicketCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        header = findViewById<View>(R.id.header) as TextView
        ticketList = findViewById<View>(R.id.list) as ListView
        databaseHelper = TicketDbHelper(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        // открываем подключение
        db = databaseHelper.readableDatabase
        //получаем данные из бд в виде курсора
        ticketCursor = db.rawQuery("select * from " + TicketDbHelper.TABLE, null)

        ticketAdapter = TicketCursorAdapter(this, ticketCursor)
        ticketList.adapter = ticketAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
        ticketCursor.close()
    }
}