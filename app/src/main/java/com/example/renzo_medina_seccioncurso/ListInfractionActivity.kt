package com.example.renzo_medina_seccioncurso

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.renzo_medina_seccioncurso.databinding.ActivityListInfractionBinding



class ListInfractionActivity : AppCompatActivity() {

    private lateinit var mySqliteHelper : MySQlite
    private lateinit var listBinding : ActivityListInfractionBinding
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listBinding = ActivityListInfractionBinding.inflate(layoutInflater)
        setContentView(listBinding.root)

        val listtoolbar = listBinding.partesToolbar
        setSupportActionBar(listtoolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        mySqliteHelper = MySQlite(this)

         db = mySqliteHelper.readableDatabase
        val cursor : Cursor= db.rawQuery("SELECT * FROM table_infractions", null)

        if (cursor.moveToFirst()){
            val adaptador = RecyclerViewAdapaterInfraction()
            adaptador.RecyclerViewAdapaterInfraction(this, cursor)
            listBinding.listInfraction.setHasFixedSize(true)
            listBinding.listInfraction.layoutManager = LinearLayoutManager(this)
            listBinding.listInfraction.adapter = adaptador

        }else{
            Toast.makeText(this, "No hay datos para mostrar", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}