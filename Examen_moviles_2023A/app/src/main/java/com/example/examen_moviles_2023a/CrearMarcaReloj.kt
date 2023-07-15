package com.example.examen_moviles_2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearMarcaReloj : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_marca_reloj)

        val crearAceptarMR = findViewById<Button>(R.id.btn_crearaceptar_mr)
        crearAceptarMR.setOnClickListener() {
            crearMarcaReloj()
        }
    }

    private fun crearMarcaReloj(){
        val textNombre = findViewById<EditText>(R.id.txr_crear_nombre_MR)
        val textPaisOrigen = findViewById<EditText>(R.id.txt_crear_pais_mr)
        val textFechaFun = findViewById<EditText>(R.id.txt_crear_date_mr)
        val textRating = findViewById<EditText>(R.id.txt_crear_rating_mr)

        BBaseDatosMemoria.arregloBMarcaReloj.add(BMarcaReloj(
            textNombre.text.toString(),textPaisOrigen.text.toString(), textFechaFun.text.toString(), textRating.text.toString().toInt()))

        finish()
    }
}