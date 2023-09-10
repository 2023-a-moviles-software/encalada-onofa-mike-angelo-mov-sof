package com.example.examen_moviles_2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarReloj : AppCompatActivity() {

    var id: String? = null
    var idMarcaReloj: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_reloj)

        this.idMarcaReloj = intent.getStringExtra("idMarcaReloj")
        this.id = intent.getStringExtra("id")
        val modelo = intent.getStringExtra("modelo")
        val precio = intent.getStringExtra("precio")
        val fechaFab = intent.getStringExtra("fechaFab")

        val txtModelo = findViewById<EditText>(R.id.txt_edit_modelo_r)
        txtModelo.setText(modelo, TextView.BufferType.EDITABLE)

        val txtPrecio = findViewById<EditText>(R.id.txt_edit_precio_r)
        txtPrecio.setText(precio, TextView.BufferType.EDITABLE)

        val txtFechaFab = findViewById<EditText>(R.id.txt_edit_fecha_r)
        txtFechaFab.setText(fechaFab, TextView.BufferType.EDITABLE)


        val botonAceptarEditarReloj = findViewById<Button>(R.id.btn_editar_r)
        botonAceptarEditarReloj.setOnClickListener() {
            devolverRespuesta()
        }
    }

    private fun devolverRespuesta() {
        val intentDevolverParametros = Intent()

        intentDevolverParametros.putExtra("idMarcaRelojDevuelto", this.idMarcaReloj)

        intentDevolverParametros.putExtra("idDevuelto", this.id)

        val txtModelo = findViewById<EditText>(R.id.txt_edit_modelo_r)
        intentDevolverParametros.putExtra("modeloModificado", txtModelo.text.toString())

        val txtPrecio = findViewById<EditText>(R.id.txt_edit_precio_r)
        intentDevolverParametros.putExtra("precioModificado", txtPrecio.text.toString())

        val txtFechaFab = findViewById<EditText>(R.id.txt_edit_fecha_r)
        intentDevolverParametros.putExtra("fechaFabModificada", txtFechaFab.text.toString())


        setResult(
            RESULT_OK,
            intentDevolverParametros
        )

        finish()
    }
}