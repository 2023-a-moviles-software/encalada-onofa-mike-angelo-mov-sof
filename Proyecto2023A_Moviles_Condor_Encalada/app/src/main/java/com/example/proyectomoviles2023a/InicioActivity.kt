package com.example.proyectomoviles2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioActivity : AppCompatActivity() {

    private var authActual = FirebaseAuth.getInstance().currentUser

    val arregloNotasRecordatorios = arrayListOf<NotaRecordatorio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val anadirButton = findViewById<Button>(R.id.btn_ia_anadir)

        anadirButton.setOnClickListener {
            irAActividad(AnadirActivity::class.java)
        }


    }


    override fun onResume() {
        super.onResume()
        inicializarRecyclerView()
    }


    private fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_ia_notasrecord)

        val adaptador = RecyclerViewAdaptorNotaRecordatorio(
            this,
            arregloNotasRecordatorios,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

        consultarNotasRecordatorios(adaptador)

    }

    public fun consultarNotasRecordatorios(adaptador: RecyclerViewAdaptorNotaRecordatorio) {
        val db = Firebase.firestore

        val notasRecordatorios = db.collection("usuarios").document(authActual!!.uid).collection("notas_recordatorios")
        //val notasRecordatorios = db.collection("notas_recordatorios")

        limpiarArreglo()
        adaptador.notifyDataSetChanged()

        notasRecordatorios
            .get()
            .addOnSuccessListener {
                for(notaRecordatorio in it){
                    anadirAAregloNoraRecordatorio(notaRecordatorio, notaRecordatorio.id)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {

            }

    }

    private fun anadirAAregloNoraRecordatorio(notaRecordatorio: QueryDocumentSnapshot, idNotaRecordatorio: String) {
        val nuevaNotaRecordatorio = NotaRecordatorio(
            idNotaRecordatorio,
            notaRecordatorio.data.get("tipo").toString(),
            notaRecordatorio.data.get("titulo").toString(),
            notaRecordatorio.data.get("descripcion").toString(),
            notaRecordatorio.data.get("categoria").toString(),
            null
        )
        if(nuevaNotaRecordatorio.tipo == "Recordatorio"){
            nuevaNotaRecordatorio.setRecordatorioCalendar(notaRecordatorio.data.get("recordatorio").toString())
        }

        this.arregloNotasRecordatorios.add(nuevaNotaRecordatorio)

    }

    private fun limpiarArreglo() {
        this.arregloNotasRecordatorios.clear()
    }

    private fun irAActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}