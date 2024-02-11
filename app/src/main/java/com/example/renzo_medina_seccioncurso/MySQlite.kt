package com.example.renzo_medina_seccioncurso

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText


class MySQlite(context : Context): SQLiteOpenHelper(context, "municipalidad.pelotillehue", null, 1) {

    //To can use database variables
    companion object{
        val SNameTable = "table_infractions"
        val SFieldRut = "rut"
        val SFieldFolio = "folio"
        val SFieldNameFactory= "name_factory"
        val SFieldAddress = "address"
        val SFieldObsevartions = "observations"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $SNameTable" + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$SFieldRut TEXT, $SFieldNameFactory TEXT," +
                "$SFieldAddress TEXT, $SFieldObsevartions TEXT,"+
                "$SFieldFolio TEXT )"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val deleteTable = "DROP TABLE IF EXISTS $SNameTable"
        db!!.execSQL(deleteTable)
        onCreate(db)
    }
    //function CRUD

    fun createData(rut:String, name : String, address: String, observation: String, folio:String){
        try {
            val data = ContentValues()
            data.put(SFieldRut, rut)
            data.put(SFieldNameFactory, name)
            data.put(SFieldAddress, address)
            data.put(SFieldObsevartions, observation)
            data.put(SFieldFolio, folio)

            val db = this.writableDatabase
            db.insert(SNameTable, null, data)
            db.close()
        }catch (e: Exception){
            Log.v("Error de base de datos", "${e.printStackTrace()}")
        }
    }

    fun deleteData(id :Int):Int{
        val args = arrayOf(id.toString())
        val db = this.writableDatabase
        val result = db.delete(SNameTable,"_id = ?", args)
        db.close()
        return result
    }


    fun updateData(id:Int, rut:String, name : String, address: String, observation: String){
        val args = arrayOf(id.toString())
        try {
            val data = ContentValues()
            data.put(SFieldRut, rut)
            data.put(SFieldNameFactory, name)
            data.put(SFieldAddress, address)
            data.put(SFieldObsevartions, observation)

            val db = this.writableDatabase
            db.update(SNameTable,data,"_id = ?", args)

            db.close()
        }catch (e: Exception){
            Log.v("Error al actualizar datos", "${e.printStackTrace()}")
        }
    }
}