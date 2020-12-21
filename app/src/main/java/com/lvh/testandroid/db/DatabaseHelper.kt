package com.lvh.testandroid.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lvh.testandroid.model.UserSave
import java.util.*

class DatabaseHelper(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    /**
     * Our onCreate() method.
     * Called when the database is created for the first time. This is
     * where the creation of tables and the initial population of the tables
     * should happen.
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY " +
                "AUTOINCREMENT,name,email, phone,location, TEXT)")
    }

    /**
     * Let's create Our onUpgrade method
     * Called when the database needs to be upgraded. The implementation should
     * use this method to drop tables, add tables, or do anything else it needs
     * to upgrade to the new schema version.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    /**
     * Let's create our insertData() method.
     * It Will insert data to SQLIte database.
     */
    fun insertData(userSave: UserSave) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, userSave.name)
        contentValues.put(COL_3, userSave.email)
        contentValues.put(COL_4, userSave.phone)
        contentValues.put(COL_5, userSave.location)
        db.insert(TABLE_NAME, null, contentValues)
    }

    /**
     * Let's create  a method to update a row with new field values.
     */
    fun updateData(userSave: UserSave):
            Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, userSave.id)
        contentValues.put(COL_2, userSave.name)
        contentValues.put(COL_3, userSave.email)
        contentValues.put(COL_4, userSave.phone)
        contentValues.put(COL_5, userSave.location)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(userSave.id.toString()))
        return true
    }

    /**
     * Let's create a function to delete a given row based on the id.
     */
    fun deleteData(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID = ?", arrayOf(id))
    }

    /**
     * The below getter property will return a Cursor containing our dataset.
     */
    fun getAllUser(): List<UserSave> {
        var listUser: MutableList<UserSave> = ArrayList<UserSave>()
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
        res.moveToFirst()
        var id: Int
        var name: String
        var email: String
        var phone: String
        var loaction: String
        val buffer = StringBuffer()
        while (res.moveToNext()) {
            id = res.getInt(res.getColumnIndex(COL_1))
            name = res.getString(res.getColumnIndex(COL_2))
            email = res.getString(res.getColumnIndex(COL_3))
            phone = res.getString(res.getColumnIndex(COL_4))
            loaction = res.getString(res.getColumnIndex(COL_5))

            listUser.add(UserSave(id,name,email,phone,loaction))
        }


        return listUser
    }

    /**
     * Let's create a companion object to hold our static fields.
     * A Companion object is an object that is common to all instances of a given
     * class.
     */
    companion object {
        val DATABASE_NAME = "user.db"
        val TABLE_NAME = "user_table"
        val COL_1 = "id"
        val COL_2 = "name"
        val COL_3 = "email"
        val COL_4 = "phone"
        val COL_5 = "location"
    }
}