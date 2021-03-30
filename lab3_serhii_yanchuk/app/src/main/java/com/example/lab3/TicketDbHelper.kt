package com.example.lab3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class TicketDbHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        SCHEMA
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE " + TABLE + " (" + COLUMN_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DEPARTURE
                    + " TEXT, " + COLUMN_ARRIVAL
                    + " TEXT, " + COLUMN_TIME
                    + " TEXT);"
        )

    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "ticketstore.db" // название бд
        private const val SCHEMA = 1 // версия базы данных
        const val TABLE = "tickets" // название таблицы в бд

        // названия столбцов
        const val COLUMN_ID = "_id"
        const val COLUMN_DEPARTURE = "departure"
        const val COLUMN_ARRIVAL = "arrival"
        const val COLUMN_TIME = "time"
    }
}