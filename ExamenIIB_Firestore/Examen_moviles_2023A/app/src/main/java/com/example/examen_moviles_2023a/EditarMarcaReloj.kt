package com.example.examen_moviles_2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EditarMarcaReloj : AppCompatActivity() {

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca_reloj)

        this.id = intent.getStringExtra("id")
        val nombre = intent.getStringExtra("nombre")
        val paisOrigen = intent.getStringExtra("paisOrigen")
        val fechaFundacion = intent.getStringExtra("fechaFun")
        val rating = "" + intent.getIntExtra("rating", 0)

        val textNombre = findViewById<EditText>(R.id.ptxt_nombre_mr)
        textNombre.setText(nombre, TextView.BufferType.EDITABLE)

        val textPaisOrigen = findViewById<EditText>(R.id.ptxt_paisorigen_mr)
        textPaisOrigen.setText(paisOrigen, TextView.BufferType.EDITABLE)

        val textfechaFundacion = findViewById<EditText>(R.id.dtxt_fechafun_mr)
        textfechaFundacion.setText(fechaFundacion, TextView.BufferType.EDITABLE)

        val textRating = findViewById<EditText>(R.id.ntxt_rating_mr)
        textRating.setText(rating, TextView.BufferType.EDITABLE)

        val botonEditar = findViewById<Button>(R.id.btn_editar_mr)
        botonEditar.setOnClickListener(){
            try {
                devolverRespuesta()
            } catch (e: Exception) {
                val errorMessage = "Ocurrió un error: ${e.message}"
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun devolverRespuesta() {
        val intentDevolverParametros = Intent()

        intentDevolverParametros.putExtra("idDevuelto", this.id)

        val textNombre = findViewById<EditText>(R.id.ptxt_nombre_mr)
        intentDevolverParametros.putExtra("nombreModificado", textNombre.text.toString())

        val textPaisOrigen = findViewById<EditText>(R.id.ptxt_paisorigen_mr)
        intentDevolverParametros.putExtra("paisOrigenModificado", textPaisOrigen.text.toString())

        val fechaFundacion = findViewById<EditText>(R.id.dtxt_fechafun_mr)
        intentDevolverParametros.putExtra("fechaFunModificada", fechaFundacion.text.toString())

        val textRating = findViewById<EditText>(R.id.ntxt_rating_mr)
        intentDevolverParametros.putExtra("ratingfModificado", textRating.text.toString())

        setResult(
            RESULT_OK,
            intentDevolverParametros
        )

        finish()
    }
}