package com.example.calculadoraumb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // --- COMPONENTES DE LA PANTALLA PRINCIPAL ---

    private lateinit var imgLogoInicio: ImageButton
    private lateinit var btnOperaciones: Button
    private lateinit var btnAcercaDe: Button

    // --- FUNCIÓN QUE SE EJECUTA AL ABRIR LA APLICACIÓN ---

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- CONECTA EL ARCHIVO KOTLIN CON activity_main.xml ---

        setContentView(R.layout.activity_main)

        // --- RELACIONA LAS VARIABLES CON LOS ELEMENTOS DEL XML ---

        imgLogoInicio = findViewById(R.id.imgLogoInicio)
        btnOperaciones = findViewById(R.id.btnOperaciones)
        btnAcercaDe = findViewById(R.id.btnAcercaDe)

        // --- EL LOGO MANTIENE O REGRESA AL INICIO ---

        imgLogoInicio.setOnClickListener {
            regresarAlInicio()
        }

        // --- ABRE LA PANTALLA DE OPERACIONES ---

        btnOperaciones.setOnClickListener {
            val intent = Intent(
                this,
                OperacionesActivity::class.java
            )

            startActivity(intent)
        }

        // --- ABRE LA PANTALLA ACERCA DE ---

        btnAcercaDe.setOnClickListener {
            val intent = Intent(
                this,
                AcercaDeActivity::class.java
            )

            startActivity(intent)
        }
    }

    // --- EVITA CREAR VARIAS COPIAS DE LA PANTALLA PRINCIPAL ---

    private fun regresarAlInicio() {
        val intent = Intent(
            this,
            MainActivity::class.java
        )

        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP

        startActivity(intent)
    }
}