package com.example.calculadoraumb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


// --- PANTALLA DE SELECCIÓN DE OPERACIONES ---

class OperacionesActivity : AppCompatActivity() {

    // --- VARIABLES DE LOS COMPONENTES VISUALES ---

    private lateinit var imgLogoOperaciones: ImageButton
    private lateinit var btnSuma: Button
    private lateinit var btnResta: Button
    private lateinit var btnMultiplicacion: Button
    private lateinit var btnDivision: Button
    private lateinit var btnVolverInicioOperaciones: Button


    // --- FUNCIÓN INICIAL DE LA ACTIVIDAD ---

    override fun onCreate(savedInstanceState: Bundle?) {

        // Ejecuta el comportamiento inicial de Android.
        super.onCreate(savedInstanceState)

        // Relaciona esta clase con activity_operaciones.xml.
        setContentView(R.layout.activity_operaciones)


        // --- CONEXIÓN DE LAS VARIABLES CON EL XML ---

        imgLogoOperaciones =
            findViewById(R.id.imgLogoOperaciones)

        btnSuma =
            findViewById(R.id.btnSuma)

        btnResta =
            findViewById(R.id.btnResta)

        btnMultiplicacion =
            findViewById(R.id.btnMultiplicacion)

        btnDivision =
            findViewById(R.id.btnDivision)

        btnVolverInicioOperaciones =
            findViewById(R.id.btnVolverInicioOperaciones)


        // --- EVENTO DEL LOGO ---

        imgLogoOperaciones.setOnClickListener {

            // Regresa directamente a MainActivity.
            regresarAlInicio()
        }


        // --- EVENTO DEL BOTÓN SUMA ---

        btnSuma.setOnClickListener {

            /*
                Se envía el texto SUMA a la siguiente pantalla.

                Ese texto permitirá saber qué cálculo debe realizarse.
            */

            abrirCalculadora("SUMA")
        }


        // --- EVENTO DEL BOTÓN RESTA ---

        btnResta.setOnClickListener {

            // Envía la identificación de la resta.
            abrirCalculadora("RESTA")
        }


        // --- EVENTO DEL BOTÓN MULTIPLICACIÓN ---

        btnMultiplicacion.setOnClickListener {

            // Envía la identificación de la multiplicación.
            abrirCalculadora("MULTIPLICACION")
        }


        // --- EVENTO DEL BOTÓN DIVISIÓN ---

        btnDivision.setOnClickListener {

            // Envía la identificación de la división.
            abrirCalculadora("DIVISION")
        }


        // --- EVENTO DEL BOTÓN VOLVER AL INICIO ---

        btnVolverInicioOperaciones.setOnClickListener {

            // Llama la misma función utilizada por el logo.
            regresarAlInicio()
        }
    }


    // --- FUNCIÓN PARA ABRIR LA CALCULADORA ---

    /*
        La función recibe una cadena llamada operacion.

        Dependiendo del botón presionado, esa cadena puede ser:
        SUMA, RESTA, MULTIPLICACION o DIVISION.
    */

    private fun abrirCalculadora(operacion: String) {

        // Se crea la navegación hacia CalculatorActivity.
        val intent = Intent(
            this,
            CalculatorActivity::class.java
        )

        /*
            putExtra permite adjuntar información al Intent.

            OPERACION_SELECCIONADA es la llave para identificar el dato.

            operacion es el valor que estamos enviando.
        */

        intent.putExtra(
            "OPERACION_SELECCIONADA",
            operacion
        )

        // Abre la pantalla de cálculo.
        startActivity(intent)
    }


    // --- FUNCIÓN PARA REGRESAR AL INICIO ---

    private fun regresarAlInicio() {

        // Se crea la navegación hacia MainActivity.
        val intent = Intent(
            this,
            MainActivity::class.java
        )

        /*
            Las banderas limpian las pantallas que se encuentran
            encima de MainActivity.
        */

        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP

        // Abre o recupera MainActivity.
        startActivity(intent)

        /*
            finish cierra OperacionesActivity para que no permanezca
            acumulada en la memoria de navegación.
        */

        finish()
    }
}