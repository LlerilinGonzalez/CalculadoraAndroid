package com.example.calculadoraumb

// Intent se usa para navegar entre actividades.
import android.content.Intent

// Bundle se usa en el ciclo de vida de la actividad.
import android.os.Bundle

// Componentes gráficos utilizados.
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

// Toast permite mostrar mensajes breves en pantalla.
import android.widget.Toast

// Clase base para la actividad.
import androidx.appcompat.app.AppCompatActivity

// DecimalFormat permite controlar la forma de mostrar el resultado.
import java.text.DecimalFormat


// --- ACTIVIDAD ENCARGADA DE REALIZAR LOS CÁLCULOS ---

class CalculatorActivity : AppCompatActivity() {

    // --- DECLARACIÓN DE LOS COMPONENTES VISUALES ---

    private lateinit var imgLogoCalculadora: ImageButton
    private lateinit var txtNombreOperacion: TextView
    private lateinit var txtSimboloOperacion: TextView
    private lateinit var edtPrimerNumero: EditText
    private lateinit var edtSegundoNumero: EditText
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnVolverOperaciones: Button


    // --- VARIABLE QUE GUARDA LA OPERACIÓN SELECCIONADA ---

    /*
        Se establece SUMA como valor inicial.

        Si por algún motivo la pantalla no recibe una operación,
        la aplicación utilizará SUMA como opción predeterminada.
    */

    private var operacionSeleccionada: String = "SUMA"


    // --- FUNCIÓN QUE SE EJECUTA CUANDO SE ABRE LA ACTIVIDAD ---

    override fun onCreate(savedInstanceState: Bundle?) {

        // Ejecuta la configuración original de Android.
        super.onCreate(savedInstanceState)

        // Conecta esta clase con activity_calculator.xml.
        setContentView(R.layout.activity_calculator)


        // --- RELACIÓN DE LOS COMPONENTES CON EL XML ---

        imgLogoCalculadora =
            findViewById(R.id.imgLogoCalculadora)

        txtNombreOperacion =
            findViewById(R.id.txtNombreOperacion)

        txtSimboloOperacion =
            findViewById(R.id.txtSimboloOperacion)

        edtPrimerNumero =
            findViewById(R.id.edtPrimerNumero)

        edtSegundoNumero =
            findViewById(R.id.edtSegundoNumero)

        txtResultado =
            findViewById(R.id.txtResultado)

        btnCalcular =
            findViewById(R.id.btnCalcular)

        btnLimpiar =
            findViewById(R.id.btnLimpiar)

        btnVolverOperaciones =
            findViewById(R.id.btnVolverOperaciones)


        // --- RECEPCIÓN DE LA OPERACIÓN SELECCIONADA ---

        /*
            getStringExtra recupera el dato enviado
            desde OperacionesActivity.

            Se utiliza la misma llave:
            OPERACION_SELECCIONADA

            El operador ?: significa que, si el dato no existe,
            se utilizará SUMA.
        */

        operacionSeleccionada =
            intent.getStringExtra(
                "OPERACION_SELECCIONADA"
            ) ?: "SUMA"


        // --- CONFIGURACIÓN DE LA INTERFAZ ---

        /*
            Se llama la función para mostrar el título
            y el símbolo correspondientes.
        */

        configurarOperacion()


        // --- EVENTO DEL LOGO ---

        imgLogoCalculadora.setOnClickListener {

            // Regresa a la pantalla principal.
            regresarAlInicio()
        }


        // --- EVENTO DEL BOTÓN CALCULAR ---

        btnCalcular.setOnClickListener {

            /*
                Al tocar el botón se validan los campos,
                se realiza la operación y se muestra el resultado.
            */

            realizarCalculo()
        }


        // --- EVENTO DEL BOTÓN LIMPIAR ---

        btnLimpiar.setOnClickListener {

            // Vacía los campos y reinicia el resultado.
            limpiarCampos()
        }


        // --- EVENTO PARA VOLVER A OPERACIONES ---

        btnVolverOperaciones.setOnClickListener {

            /*
                finish cierra la actividad actual.

                Como OperacionesActivity está debajo en la pila,
                Android vuelve a mostrarla.
            */

            finish()
        }
    }


    // --- FUNCIÓN PARA CONFIGURAR EL NOMBRE Y SÍMBOLO ---

    /*
        Esta función analiza la operación recibida.

        Dependiendo del valor, modifica dos TextView:
        txtNombreOperacion y txtSimboloOperacion.
    */

    private fun configurarOperacion() {

        /*
            when permite evaluar diferentes posibilidades.

            Es similar a switch en otros lenguajes.
        */

        when (operacionSeleccionada) {

            // --- CONFIGURACIÓN DE LA SUMA ---

            "SUMA" -> {

                /*
                    getString obtiene el texto guardado
                    en strings.xml.
                */

                txtNombreOperacion.text =
                    getString(R.string.suma)

                txtSimboloOperacion.text = "+"
            }


            // --- CONFIGURACIÓN DE LA RESTA ---

            "RESTA" -> {

                txtNombreOperacion.text =
                    getString(R.string.resta)

                txtSimboloOperacion.text = "−"
            }


            // --- CONFIGURACIÓN DE LA MULTIPLICACIÓN ---

            "MULTIPLICACION" -> {

                txtNombreOperacion.text =
                    getString(R.string.multiplicacion)

                txtSimboloOperacion.text = "×"
            }


            // --- CONFIGURACIÓN DE LA DIVISIÓN ---

            "DIVISION" -> {

                txtNombreOperacion.text =
                    getString(R.string.division)

                txtSimboloOperacion.text = "÷"
            }


            // --- CONFIGURACIÓN PREDETERMINADA ---

            else -> {

                /*
                    Si el valor recibido no coincide con ninguna
                    operación, la calculadora utilizará suma.
                */

                txtNombreOperacion.text =
                    getString(R.string.suma)

                txtSimboloOperacion.text = "+"

                operacionSeleccionada = "SUMA"
            }
        }
    }


    // --- FUNCIÓN PRINCIPAL DEL CÁLCULO ---

    /*
        Esta función realiza cuatro procesos:

        1. Obtiene los textos escritos.
        2. Valida que los campos no estén vacíos.
        3. Convierte los textos en números.
        4. Realiza la operación seleccionada.
    */

    private fun realizarCalculo() {

        // --- OBTENCIÓN DEL TEXTO DEL PRIMER CAMPO ---

        /*
            text obtiene el contenido del EditText.

            toString convierte el contenido a texto normal.

            trim elimina espacios al principio y al final.
        */

        val primerTexto =
            edtPrimerNumero.text.toString().trim()


        // --- OBTENCIÓN DEL TEXTO DEL SEGUNDO CAMPO ---

        val segundoTexto =
            edtSegundoNumero.text.toString().trim()


        // --- VALIDACIÓN DE CAMPOS VACÍOS ---

        /*
            isEmpty comprueba si no hay contenido.

            || significa "o".

            La condición se cumple si el primer campo
            o el segundo campo están vacíos.
        */

        if (
            primerTexto.isEmpty() ||
            segundoTexto.isEmpty()
        ) {

            /*
                Toast muestra un mensaje temporal
                en la parte inferior de la pantalla.
            */

            Toast.makeText(
                this,
                getString(R.string.campos_obligatorios),
                Toast.LENGTH_SHORT
            ).show()

            /*
                return detiene la función.

                Así se evita continuar intentando calcular
                cuando faltan valores.
            */

            return
        }


        // --- CONVERSIÓN DE TEXTO A NÚMERO ---

        /*
            toDoubleOrNull intenta convertir el texto en Double.

            Double permite trabajar con números decimales.

            Si la conversión no es posible, devuelve null
            en lugar de cerrar la aplicación.
        */

        val primerNumero =
            primerTexto.toDoubleOrNull()

        val segundoNumero =
            segundoTexto.toDoubleOrNull()


        // --- VALIDACIÓN DE NÚMEROS INVÁLIDOS ---

        /*
            null indica que el texto no pudo convertirse
            correctamente en un número.
        */

        if (
            primerNumero == null ||
            segundoNumero == null
        ) {

            Toast.makeText(
                this,
                getString(R.string.numero_invalido),
                Toast.LENGTH_SHORT
            ).show()

            // Detiene el cálculo.
            return
        }


        // --- SELECCIÓN Y EJECUCIÓN DE LA OPERACIÓN ---

        /*
            La variable resultado será de tipo Double.

            when devuelve directamente el valor obtenido
            en la operación seleccionada.
        */

        val resultado: Double =
            when (operacionSeleccionada) {


                // --- OPERACIÓN DE SUMA ---

                "SUMA" -> {

                    // Suma los dos números.
                    primerNumero + segundoNumero
                }


                // --- OPERACIÓN DE RESTA ---

                "RESTA" -> {

                    // Resta el segundo número al primero.
                    primerNumero - segundoNumero
                }


                // --- OPERACIÓN DE MULTIPLICACIÓN ---

                "MULTIPLICACION" -> {

                    // Multiplica ambos números.
                    primerNumero * segundoNumero
                }


                // --- OPERACIÓN DE DIVISIÓN ---

                "DIVISION" -> {

                    // !!! VALIDACIÓN DE DIVISIÓN ENTRE CERO !!!

                    /*
                        Antes de dividir se comprueba que
                        el segundo número no sea cero.
                    */

                    if (segundoNumero == 0.0) {

                        Toast.makeText(
                            this,
                            getString(R.string.division_cero),
                            Toast.LENGTH_SHORT
                        ).show()

                        // Detiene la función para evitar el cálculo.
                        return
                    }

                    // Divide el primer número entre el segundo.
                    primerNumero / segundoNumero
                }


                // --- OPERACIÓN PREDETERMINADA ---

                else -> {

                    /*
                        Si no se reconoce la operación,
                        se realiza una suma.
                    */

                    primerNumero + segundoNumero
                }
            }


        // --- PRESENTACIÓN DEL RESULTADO ---

        /*
            Se envía el resultado a otra función
            encargada de darle formato.
        */

        mostrarResultado(resultado)
    }


    // --- FUNCIÓN PARA FORMATEAR EL RESULTADO ---

    /*
        Esta función evita mostrar resultados como:

        15.00000000

        En su lugar puede mostrar:

        15
        15.5
        15.25
    */

    private fun mostrarResultado(resultado: Double) {

        /*
            DecimalFormat controla la cantidad de decimales.

            El patrón permite hasta ocho decimales,
            pero no muestra ceros innecesarios.
        */

        val formato =
            DecimalFormat("#,##0.########")

        /*
            format convierte el número en texto
            con el formato establecido.

            Después se asigna ese texto al TextView.
        */

        txtResultado.text =
            formato.format(resultado)
    }


    // --- FUNCIÓN PARA LIMPIAR LA CALCULADORA ---

    /*
        Esta función devuelve el formulario
        a su estado inicial.
    */

    private fun limpiarCampos() {

        // Elimina el contenido del primer campo.
        edtPrimerNumero.text.clear()

        // Elimina el contenido del segundo campo.
        edtSegundoNumero.text.clear()

        // Restablece el mensaje inicial del resultado.
        txtResultado.text =
            getString(R.string.resultado_inicial)

        /*
            requestFocus coloca el cursor nuevamente
            en el primer campo.
        */

        edtPrimerNumero.requestFocus()
    }


    // --- FUNCIÓN PARA VOLVER AL INICIO ---

    private fun regresarAlInicio() {

        // Se crea el Intent hacia MainActivity.
        val intent = Intent(
            this,
            MainActivity::class.java
        )

        /*
            Se limpian las actividades superiores
            para no acumular pantallas.
        */

        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP

        // Se abre la pantalla principal.
        startActivity(intent)

        // Se cierra la calculadora actual.
        finish()
    }
}