package com.example.examen_moviles_2023a

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class ListViewRelojes : AppCompatActivity() {

    var listaRelojesFireBase: ArrayList<BReloj> = arrayListOf<BReloj>()
    var listaRelojes: ArrayList<BReloj>? = null
    var idItemSeleccionado = 0
    var idMarcaReloj: String? = null


    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    // Logica Negocio
                    val data = result.data

                    val db = Firebase.firestore
                    val relojRef = db.collection("marca_relojes")
                        .document(data?.getStringExtra("idMarcaRelojDevuelto").toString())
                        .collection("relojes")
                        .document(data?.getStringExtra("idDevuelto").toString())

                    Log.w("idMarcaDevuelto",data?.getStringExtra("idMarcaDevuelto").toString())
                    Log.w("idDevuelto",data?.getStringExtra("idDevuelto").toString())

                    val relojActualizado = hashMapOf(
                        "modelo" to data?.getStringExtra("modeloModificado").toString(),
                        "precio" to data?.getStringExtra("precioModificado")?.toDouble(),
                        "fechaFabricacion" to data?.getStringExtra("fechaFabModificada").toString()
                    )

                    relojRef
                        .set(
                            relojActualizado
                        )
                        .addOnSuccessListener {  }
                        .addOnSuccessListener {  }

                    actualizarListView()


                }
            }
        }


    val callbackContenidoIntentExplicito1 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    // Logica Negocio
                    val data = result.data

                    val db = Firebase.firestore
                    val relojesRefUnico = db.collection("marca_relojes").document(this.idMarcaReloj!!).collection("relojes")

                    val relojActualizado = hashMapOf(
                        "modelo" to data?.getStringExtra("modeloModificado").toString(),
                        "precio" to data?.getStringExtra("precioModificado")?.toDouble(),
                        "fechaFabricacion" to data?.getStringExtra("fechaFabModificada").toString()
                    )

                    relojesRefUnico
                        .add(relojActualizado)
                        .addOnSuccessListener {  }
                        .addOnFailureListener {  }

                    actualizarListView()

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_relojes)

        this.idMarcaReloj = intent.getStringExtra("idMarcaReloj")


        val txtTituloMarcaReloj = findViewById<TextView>(R.id.txtV_titulo_mr)
        txtTituloMarcaReloj.setText(intent.getStringExtra("nombreMarca"))

        actualizarListView()

        val botonCrearReloj = findViewById<Button>(R.id.btn_crear_reloj)
        botonCrearReloj.setOnClickListener(){
            abrirActividadCrearR(CrearReloj::class.java)
        }


    }



    fun actualizarListView(){


        //this.listaRelojesSQL = EBaseDeDatos.tablaMarcaReloj.consultarTodosLosRelojesDeMR(this.idMarcaReloj)


        val listViewR = findViewById<ListView>(R.id.lv_relojes)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaRelojesFireBase!!
        )

        listViewR.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listViewR)

        consultarMarcaRelojes(adaptador)

    }

    private fun consultarMarcaRelojes(adaptador: ArrayAdapter<BReloj>) {
        val db = Firebase.firestore
        val relojesRefUnico = db.collection("marca_relojes").document(this.idMarcaReloj!!).collection("relojes")

        limpiarArreglo()
        adaptador.notifyDataSetChanged()

        relojesRefUnico
            .get()
            .addOnSuccessListener {
                for (reloj in it){
                    anadirAArregloReloj(reloj, reloj.id)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {  }
    }

    private fun anadirAArregloReloj(reloj: QueryDocumentSnapshot, idReloj: String) {
        val nuevoReloj = BReloj(
            idReloj,
            reloj.data.get("modelo").toString(),
            reloj.data.get("precio").toString().toDouble(),
            reloj.data.get("fechaFabricacion").toString()
        )

        this.listaRelojesFireBase.add(nuevoReloj)
    }

    private fun limpiarArreglo() {
        this.listaRelojesFireBase.clear()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu1, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar1 ->{
                abrirConParametrosEditarR(EditarReloj::class.java)
                return true
            }
            R.id.mi_eliminar1 ->{
                abrirDialogoEliminar()
                actualizarListView()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirConParametrosEditarR(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        val reloj = listaRelojesFireBase?.get(idItemSeleccionado)
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

        intentExplicito.putExtra("idMarcaReloj", this.idMarcaReloj)
        intentExplicito.putExtra("id", reloj?.id)
        intentExplicito.putExtra("modelo", reloj?.modelo)
        intentExplicito.putExtra("precio", reloj?.precio.toString())
        intentExplicito.putExtra("fechaFab", formatoFecha.format(reloj?.fechaFabricacion))


        callbackContenidoIntentExplicito.launch(intentExplicito)

    }

    fun abrirActividadCrearR(clase: Class<*>){
        val intent = Intent(this, clase)

        intent.putExtra("id", this.idMarcaReloj)

        callbackContenidoIntentExplicito1.launch(intent)
    }


    fun abrirDialogoEliminar(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton("Aceptar") {
                dialog, which ->

            val reloj = listaRelojesFireBase.get(idItemSeleccionado)
            val db = Firebase.firestore

            val relojRef = db.collection("marca_relojes")
                .document(this.idMarcaReloj!!)
                .collection("relojes")
                .document(reloj.id!!)

            relojRef
                .delete()
                .addOnSuccessListener{}
                .addOnFailureListener {  }
            //EBaseDeDatos.tablaMarcaReloj.eliminarReloj(listaRelojesSQL?.get(idItemSeleccionado)!!.id?:0)
            actualizarListView()

        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()


    }


}