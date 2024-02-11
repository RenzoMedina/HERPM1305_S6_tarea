package com.example.renzo_medina_seccioncurso


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.renzo_medina_seccioncurso.databinding.ActivityInfractionBinding


class InfractionActivity : AppCompatActivity() {
    private lateinit var infractionBinding: ActivityInfractionBinding
    private lateinit var mySqliteHelper: MySQlite
    //inicio de folio
    private var numero = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        infractionBinding = ActivityInfractionBinding.inflate(layoutInflater)
        setContentView(infractionBinding.root)

        val infraToolbar= infractionBinding.infractionToolbar
        setSupportActionBar(infraToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //variable
        var rut = infractionBinding.edtRut.text
        var name = infractionBinding.edtNombre.text
        var address = infractionBinding.edtDireccion.text
        var obs = infractionBinding.edtInfracion.text

        // Para que nuestro folio se puede almacenar
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        numero = sharedPreferences.getInt("NUMERO_KEY", 100)

        mySqliteHelper = MySQlite(this)
        infractionBinding.saveData.setOnClickListener {

            //numero completo de folio
            var folio ="FOLIOINFRA"+numero.toString()+"2024"
            if (rut.isNotBlank() && name.isNotBlank()){
                mySqliteHelper.createData(rut.toString(),name.toString(),address.toString(),obs.toString(),folio)
                Toast.makeText(this, "Datos guardados Folio: $folio",Toast.LENGTH_LONG).show()
                //limpia los editext
                rut.clear()
                name.clear()
                address.clear()
                obs.clear()
                //increntamos el valor
                numero+=1
                val editor = sharedPreferences.edit()
                editor.putInt("NUMERO_KEY", numero)
                editor.apply()
            }else{
                Toast.makeText(this, "Error no se guardo registro",Toast.LENGTH_SHORT).show()
            }
        }

    }

}