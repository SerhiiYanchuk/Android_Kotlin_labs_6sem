package com.example.lab3

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView

class TicketCursorAdapter(context: Context?, cursor: Cursor?) : CursorAdapter(context, cursor, 0) {

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.ticket_item_layout, parent, false)
    }

    override fun bindView(view: View, context: Context?, cursor: Cursor) {

        val tvDeparture = view.findViewById(R.id.tvDeparture) as TextView
        val tvArrival = view.findViewById(R.id.tvArrival) as TextView
        val tvTime = view.findViewById(R.id.tvTime) as TextView

        val departure: String = cursor.getString(cursor.getColumnIndexOrThrow(TicketDbHelper.COLUMN_DEPARTURE))
        val arrival: String = cursor.getString(cursor.getColumnIndexOrThrow(TicketDbHelper.COLUMN_ARRIVAL))
        val time: String = cursor.getString(cursor.getColumnIndexOrThrow(TicketDbHelper.COLUMN_TIME))

        tvDeparture.text = departure
        tvArrival.text = arrival
        tvTime.text = time
    }
}