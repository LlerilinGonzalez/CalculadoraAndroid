package com.example.calculadoraumb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


// --- PANTALLA DE INFORMACIÓN DE LA APLICACIÓN ---

class AcercaDeActivity : AppCompatActivity() {

    // --- COMPONENTES VISUALES ---

    private lateinit var imgLogoAcercaDe: ImageButton
    private lateinit var btnVolverInicioAcercaDe: Button


    // --- FUNCIÓN QUE SE EJECUTA AL ABRIR LA PANTALLA ---

    override fun onCreate(savedInstanceState: Bundle?) {

        // Ejecuta la configuración inicial de Android.
        super.onCreate(savedInstanceState)

        // Relaciona la clase con activity_acerca_de.xml.
        setContentView(R.layout.activity_acerca_de)


        // --- CONEXIÓN CON LOS COMPONENTES XML ---

        imgLogoAcercaDe =
            findViewById(R.id.imgLogoAcercaDe)

        btnVolverInicioAcercaDe =
            findViewById(R.id.btnVolverInicioAcercaDe)


        // --- EVENTO DEL LOGO ---

        imgLogoAcercaDe.setOnClickListener {

            // Regresa a la pantalla principal.
            regresarAlInicio()
        }


        // --- EVENTO DEL BOTÓN VOLVER AL INICIO ---

        btnVolverInicioAcercaDe.setOnClickListener {

            // Utiliza la misma función del logo.
            regresarAlInicio()
        }
    }


    // --- FUNCIÓN DE NAVEGACIÓN AL INICIO ---

    /*
        La función permite reutilizar la lógica
        tanto para el logo como para el botón.
    */

    private fun regresarAlInicio() {

        // Se crea un Intent hacia MainActivity.
        val intent = Intent(
            this,
            MainActivity::class.java
        )

        /*
            Estas banderas evitan crear varias copias
            de la pantalla principal.
        */

        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP

        // Abre la pantalla principal.
        startActivity(intent)

        // Cierra la actividad Acerca de.
        finish()
    }
}