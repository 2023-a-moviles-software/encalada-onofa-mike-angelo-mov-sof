package com.example.examen_moviles_2023a

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import java.text.SimpleDateFormat

class ListViewRelojes : AppCompatActivity() {

    var listaRelojes: ArrayList<BReloj>? = null
    var idItemSeleccionado = 0
    var idMarcaReloj = 0


    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    // Logica Negocio
                    val data = result.data

                    listaRelojes?.get(idItemSeleccionado)?.modelo= data?.getStringExtra("modeloModificado").toString()
                    listaRelojes?.get(idItemSeleccionado)?.setFechaFabricacion(data?.getStringExtra("fechaFabModificada").toString())
                    listaRelojes?.get(idItemSeleccionado)?.precio = data?.getStringExtra("precioModificado")?.toDouble() ?: 0.0
                    actualizarListView(this.listaRelojes!!)

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_relojes)

        this.idMarcaReloj = intent.getIntExtra("idMarcaReloj", 0)
        listaRelojes = BBaseDatosMemoria.arregloBMarcaReloj.get(this.idMarcaReloj).getRelojes()

        val txtTituloMarcaReloj = findViewById<TextView>(R.id.txtV_titulo_mr)
        txtTituloMarcaReloj.setText(BBaseDatosMemoria.arregloBMarcaReloj.get(intent.getIntExtra("idMarcaReloj", 0)).nombre)

        actualizarListView(this.listaRelojes!!)

        val botonCrearReloj = findViewById<Button>(R.id.btn_crear_reloj)
        botonCrearReloj.setOnClickListener(){
            abrirActividadCrearR(CrearReloj::class.java)
        }

    }

    override fun onResume() {
        super.onResume()
        actualizarListView(this.listaRelojes!!)
    }


    fun actualizarListView(listaRelojes: ArrayList<BReloj>){

        val listViewR = findViewById<ListView>(R.id.lv_relojes)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaRelojes
        )

        listViewR.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listViewR)

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
                actualizarListView(this.listaRelojes!!)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirConParametrosEditarR(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        val reloj = listaRelojes?.get(idItemSeleccionado)
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

        intentExplicito.putExtra("modelo", reloj?.modelo)
        intentExplicito.putExtra("precio", reloj?.precio.toString())
        intentExplicito.putExtra("fechaFab", formatoFecha.format(reloj?.fechaFabricacion))


        callbackContenidoIntentExplicito.launch(intentExplicito)

    }

    fun abrirActividadCrearR(clase: Class<*>){
        val intent = Intent(this, clase)
        intent.putExtra("id", this.idMarcaReloj)
        startActivity(intent)
    }


    fun abrirDialogoEliminar(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton("Aceptar") {
                dialog, which ->
            listaRelojes?.removeAt(idItemSeleccionado)
            actualizarListView(this.listaRelojes!!)

        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()


    }


}