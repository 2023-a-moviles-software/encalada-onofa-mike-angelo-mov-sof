package com.example.examen_moviles_2023a

import android.content.Intent
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

        val intentDevolverParametros = Intent()


        intentDevolverParametros.putExtra("idDevueltoMR", this.idMarcaReloj)

        val txtModelo = findViewById<EditText>(R.id.txt_crear_modelo_r)
        intentDevolverParametros.putExtra("modeloModificado", txtModelo.text.toString())



        val txtPrecio = findViewById<EditText>(R.id.txt_crear_precio_r)
        intentDevolverParametros.putExtra("precioModificado", txtPrecio.text.toString())


        val txtFechaFab = findViewById<EditText>(R.id.txt_crear_fecha_r)
        intentDevolverParametros.putExtra("fechaFabModificada", txtFechaFab.text.toString())


        //EBaseDeDatos.tablaMarcaReloj.crearReloj(txtModelo.toString(), txtPrecio.toString().toDouble(), txtFechaFab.toString(), this.idMarcaReloj)

        /*
        BBaseDatosMemoria.arregloBMarcaReloj.get(this.idMarcaReloj).getRelojes().add(
            BReloj(txtModelo.text.toString(), txtPrecio.text.toString().toDouble(), txtFechaFab.text.toString())
        )

         */

        setResult(
            RESULT_OK,
            intentDevolverParametros
        )


        finish()
    }
}