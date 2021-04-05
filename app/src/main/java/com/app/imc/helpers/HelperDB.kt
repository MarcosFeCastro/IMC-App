package com.app.imc.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.imc.fragments.history.Imc

class HelperDB(
    context: Context?
) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private val NAME = "imc.db"
        private val VERSION = 1
    }

    val TABLE_NAME = "imc"
    val COLUMNS_ID = "id"
    val COLUMNS_RESULT = "result"
    val COLUMNS_DIFFERENCE = "difference"
    val DROP_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMNS_ID INTEGER NOT NULL, " +
            "$COLUMNS_RESULT TEXT NOT NULL, " +
            "$COLUMNS_DIFFERENCE TEXT NOT NULL, " +
            "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT)" +
            ")"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL(DROP_TABLE)
        }
        onCreate(db)
    }

    fun selectImc(limit: Int?) : List<Imc> {
        val db = readableDatabase ?: return mutableListOf()
        var list = mutableListOf<Imc>()
        var sql = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMNS_ID DESC"
        if (limit != null) {
            sql += " LIMIT $limit"
        }
        var cursor = db.rawQuery(sql, null) ?: return mutableListOf()
        if (cursor == null) {
            db.close()
            return mutableListOf()
        }
        while (cursor.moveToNext()) {
            val imc = Imc(
                cursor.getInt(cursor.getColumnIndex(COLUMNS_ID)),
                cursor.getFloat(cursor.getColumnIndex(COLUMNS_RESULT)),
                cursor.getFloat(cursor.getColumnIndex(COLUMNS_DIFFERENCE))
            )
            list.add(imc)
        }
        db.close()
        return list
    }

    fun insertImc(imc: Imc) {
        val db = readableDatabase ?: return
        val sql = "INSERT INTO $TABLE_NAME ($COLUMNS_RESULT, $COLUMNS_DIFFERENCE) VALUES (?, ?)"
        var params: Array<Float> = arrayOf(imc.result, imc.difference)
        db.execSQL(sql, params)
        db.close()
    }

    fun deleteImc(id: Int) {
        val db = readableDatabase ?: return
        val sql = "DELETE FROM $TABLE_NAME WHERE $COLUMNS_ID = ?"
        val params: Array<String> = arrayOf("$id")
        db.execSQL(sql, params)
        db.close()
    }

}