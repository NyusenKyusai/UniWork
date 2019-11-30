package com.example.mrtayyab.sqlitedbkotlin

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.juliaojonah_hci_outcome2_v1.PasswordCluster

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    //This code is adapted from https://github.com/tayyabmughal676/SQLiteDBKotlin-ParhoLikhoCS


    companion object {

        val DATABASE_NAME = "passwords.db"
        val TABLE_NAME = "passwords_table"
        val COL_1 = "ID"
        val COL_2 = "DESCRIPTION"
        val COL_3 = "PASSWORD"
        val COL_4 = "DATE_TIME"
    }


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY , DESCRIPTION  TEXT , PASSWORD TEXT , DATE_TIME INTEGER)")

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

    fun getListOfAllPasswordClusters(): ArrayList<PasswordCluster> {
        val rv = ArrayList<PasswordCluster>()
        val db = this.writableDatabase
        val csr = db.query(TABLE_NAME,null /* ALL columns */,null,null,null,null,null)

        while (csr.moveToNext()) {

            rv.add(
                PasswordCluster(
                    csr.getLong(csr.getColumnIndex(COL_1)),
                    csr.getString(csr.getColumnIndex(COL_2)),
                    csr.getString(csr.getColumnIndex(COL_3)),
                    csr.getString(csr.getColumnIndex(COL_4))
                )
            )

        }
        csr.close()
        return rv
    }
}