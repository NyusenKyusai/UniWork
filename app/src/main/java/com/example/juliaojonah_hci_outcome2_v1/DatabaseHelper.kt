package com.example.mrtayyab.sqlitedbkotlin

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    companion object {

        val DATABASE_NAME = "passwords.db"
        val TABLE_NAME = "passwords_table"
        val COL_1 = "ID"
        val COL_2 = "DESCRIPTION"
        val COL_3 = "PASSWORD"
        val COL_4 = "DATE_TIME"
    }


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION  TEXT , PASSWORD TEXT , DATE_TIME INTEGER)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }

    fun insertData(description: String, password: String, date_time: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_2, description)
        cv.put(COL_3, password)
        cv.put(COL_4, date_time)
        val res = db.insert(TABLE_NAME, null, cv)
        return !res.equals(-1)
    }

    fun getData(id: String, COL_NUM: String): Cursor {
        val column = arrayOf("$COL_NUM")
        val columnValue = arrayOf("$id")
        val db = this.writableDatabase
        return db.query("$TABLE_NAME", column, "$COL_1", columnValue, null, null, null )
    }

    fun getTableCount(): Long {
        val db = this.readableDatabase
        val count = DatabaseUtils.queryNumEntries(db, TABLE_NAME)

        return count
    }
}