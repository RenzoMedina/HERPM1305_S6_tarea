package com.example.renzo_medina_seccioncurso

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.renzo_medina_seccioncurso.databinding.ItemInfractionBinding

class RecyclerViewAdapaterInfraction : RecyclerView.Adapter<RecyclerViewAdapaterInfraction.ViewHolder>(){

    lateinit var context: Context
    lateinit var  cursor : Cursor
    private lateinit var db: SQLiteDatabase

    @SuppressLint("NotConstructor")
    fun RecyclerViewAdapaterInfraction(context: Context, cursor : Cursor){
        this.context = context
        this.cursor = cursor
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_infraction, parent, false))
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        holder.getId.text= cursor.getString(0)
        holder.rut.text = cursor.getString(1)
        holder.nombre.text = cursor.getString(2)
        holder.direccion.text = cursor.getString(3)
        holder.obsevacion.text = cursor.getString(4)
        holder.folio.text = cursor.getString(5)
    }

    inner  class  ViewHolder : RecyclerView.ViewHolder {
        val folio :TextView
        val rut :TextView
        val nombre :TextView
        val direccion :TextView
        val obsevacion :TextView
        val getId : TextView

        constructor(view:View):super(view){
            val bindingItem = ItemInfractionBinding.bind(view)
            rut = bindingItem.rut
            nombre = bindingItem.nombre
            direccion = bindingItem.direccion
            obsevacion = bindingItem.observaciones
            folio = bindingItem.folio
            getId = bindingItem.getId

            view.setOnClickListener {
               //Toast.makeText(context,"ID: $getId.text.toString()", Toast.LENGTH_SHORT).show()
                val intento = Intent (context, UpdateInfractionActivity::class.java).apply {
                    putExtra("FOLIO", folio.text)
                    putExtra("RUT", rut.text)
                    putExtra("NOMBRE", nombre.text)
                    putExtra("DIRECCION", direccion.text)
                    putExtra("OBSERVACION", obsevacion.text)
                    putExtra("GETID", getId.text.toString())
                }
                context.startActivity(intento)

            }

        }
    }

}