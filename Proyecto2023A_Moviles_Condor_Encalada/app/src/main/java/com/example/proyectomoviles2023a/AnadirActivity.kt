package com.example.proyectomoviles2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnadirActivity : AppCompatActivity() {

    private var authActual = FirebaseAuth.getInstance().currentUser
    var spinnerTipo: Spinner? = null
    var tituloEditText: EditText? = null
    var descrpcionEditText: EditText? = null
    var spinnerCategoria: Spinner? = null
    var limiteTiempoEditar: EditText? = null
    var limiteTiempoTitulo: TextView? = null
    var botonCrear: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir)

        spinnerTipo = findViewById(R.id.s_anadira_tipospinner)
        tituloEditText = findViewById(R.id.et_anadira_tituloE)
        descrpcionEditText = findViewById(R.id.et_anadira_descripcionE)
        spinnerCategoria = findViewById(R.id.s_anadira_categoria)
        limiteTiempoEditar = findViewById(R.id.et_anadira_tiempoE)
        limiteTiempoTitulo = findViewById(R.id.tv_anadira_limiteTiempoT)
        botonCrear = findViewById(R.id.btn_anadira_crear)

        val opcionesTipo = arrayOf("Nota", "Recordatorio")
        val adaptadorSpinerTipo = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesTipo)
        adaptadorSpinerTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinnerTipo?.adapter = adaptadorSpinerTipo

        val db = Firebase.firestore
        val categoriasRef = db.collection("categorias")
        val opcionesCategoria = arrayListOf<String>()

        categoriasRef.get()
            .addOnSuccessListener { categorias ->
                for (categoria in categorias) {
                    val nombre = categoria.getString("nombre")
                    if (nombre != null) {
                        opcionesCategoria.add(nombre)
                    }
                }
                val adaptadorSpinerCategoria = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesCategoria)
                adaptadorSpinerCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.spinnerCategoria?.adapter = adaptadorSpinerCategoria
            }
            .addOnFailureListener { e ->

            }



        spinnerTipo?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val seleccion = parent?.getItemAtPosition(position).toString()

                if(seleccion == "Nota"){
                    limiteTiempoEditar?.visibility = View.INVISIBLE
                    limiteTiempoTitulo?.visibility = View.INVISIBLE
                }else{
                    limiteTiempoEditar?.visibility = View.VISIBLE
                    limiteTiempoTitulo?.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        botonCrear!!.setOnClickListener {
            crearNotaRecordatorio()
        }



    }

    private fun crearNotaRecordatorio() {

        var limiteTiempo = ""
        if(limiteTiempoEditar!!.text.isNotEmpty()){
            limiteTiempo = limiteTiempoEditar!!.text.toString()
        }

        val notaRecordatorioNuevo = hashMapOf(
            "tipo" to spinnerTipo!!.getItemAtPosition(spinnerTipo!!.selectedItemPosition).toString(),
            "titulo" to tituloEditText!!.text.toString(),
            "descripcion" to descrpcionEditText!!.text.toString(),
            "categoria" to spinnerCategoria!!.getItemAtPosition(spinnerCategoria!!.selectedItemPosition).toString(),
            "recordatorio" to limiteTiempo
        )

        val db = Firebase.firestore
        // val categoriasRef = db.collection("notas_recordatorios")
        val categoriasRef = db.collection("usuarios").document(authActual!!.uid).collection("notas_recordatorios")

        Log.e("Valor del tipo", spinnerTipo!!.getItemAtPosition(spinnerTipo!!.selectedItemPosition).toString())
        Log.e("Valor de la categoria", spinnerCategoria!!.getItemAtPosition(spinnerCategoria!!.selectedItemPosition).toString())

        categoriasRef.add(notaRecordatorioNuevo)
            .addOnSuccessListener {
                finish()
            }
            .addOnFailureListener {e ->
                Log.e("TAG", "Error al agregar la nota", e)
            }
    }


}