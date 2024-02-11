package com.example.renzo_medina_seccioncurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.renzo_medina_seccioncurso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutViews.btPartes.setOnClickListener {
            val intento = Intent(this, InfractionActivity::class.java)
            startActivity(intento)
        }

        binding.layoutViews.btListPartes.setOnClickListener {
            val intento = Intent(this, ListInfractionActivity::class.java)
            startActivity(intento)
        }


    }
}