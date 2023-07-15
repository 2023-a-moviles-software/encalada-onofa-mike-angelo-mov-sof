package com.example.examen_moviles_2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearReloj : AppCompatActivity() {

    var idMarcaReloj = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_reloj)

        this.idMarcaReloj = intent.getIntExtra("id", 0)

        val crearReloj = findViewById<Button>(R.id.btn_aceptarcrear_r)
        crearReloj.setOnClickListener(){
            crearAceptarReloj()
        }

    }

    private fun crearAceptarReloj(){
        val txtModelo = findViewById<EditText>(R.id.txt_crear_modelo_r)
        val txtPrecio = findViewById<EditText>(R.id.txt_crear_precio_r)
        val txtFechaFab = findViewById<EditText>(R.id.txt_crear_fecha_r)

        BBaseDatosMemoria.arregloBMarcaReloj.get(this.idMarcaReloj).getRelojes().add(
            BReloj(txtModelo.text.toString(), txtPrecio.text.toString().toDouble(), txtFechaFab.text.toString())
        )

        finish()
    }
}