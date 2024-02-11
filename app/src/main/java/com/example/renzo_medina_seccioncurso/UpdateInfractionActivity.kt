package com.example.renzo_medina_seccioncurso

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.renzo_medina_seccioncurso.databinding.ActivityUpdateInfractionBinding

class UpdateInfractionActivity : AppCompatActivity() {
    private  lateinit var updateBinding: ActivityUpdateInfractionBinding
    private lateinit var mySqliteHelper: MySQlite


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateBinding = ActivityUpdateInfractionBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)

        val updatetoolbar = updateBinding.updateToolbar
        setSupportActionBar(updatetoolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        val rut = intent.getStringExtra("RUT")
        val folio = intent.getStringExtra("FOLIO")
        val nombre = intent.getStringExtra("NOMBRE")
        val direccion= intent.getStringExtra("DIRECCION")
        val observacion= intent.getStringExtra("OBSERVACION")
        val getId = intent.getStringExtra("GETID")
        updateBinding.textViewFolio.text = folio
        updateBinding.upRut.hint = rut
        updateBinding.upNombre.hint = nombre
        updateBinding.upDireccion.hint = direccion
        updateBinding.upObservacion.hint = observacion

        mySqliteHelper = MySQlite(this)
        Toast.makeText(this,"Debes ingresar los datos actuales", Toast.LENGTH_LONG).show()
        updateBinding.upData.setOnClickListener {

            if(updateBinding.upRut.text.isNotBlank() && updateBinding.upNombre.text.isNotBlank()){
                mySqliteHelper.updateData(getId.toString().toInt(), updateBinding.upRut.text.toString(),updateBinding.upNombre.text.toString(),
                    updateBinding.upDireccion.text.toString(),updateBinding.upObservacion.text.toString())
                Toast.makeText(this,"Los datos han sido actualizado", Toast.LENGTH_LONG).show()
                val intento = Intent(this, ListInfractionActivity::class.java)
                startActivity(intento)
            }else{
                Toast.makeText(this,"Error no se ha podido actualizar", Toast.LENGTH_SHORT).show()
            }
        }

        //Boton compartir
        updateBinding.shareBtn.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, folio +"\n"+
                        rut +"\n"+
                        nombre+"\n"+
                        direccion+"\n"+
                        observacion)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }
}