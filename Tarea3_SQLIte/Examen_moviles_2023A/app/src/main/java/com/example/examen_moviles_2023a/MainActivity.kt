package com.example.examen_moviles_2023a

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {


    var arreglo = BBaseDatosMemoria.arregloBMarcaReloj
    var arregloMarcaReloj: ArrayList<BMarcaReloj> = arrayListOf<BMarcaReloj>()
    var idItemSeleccionado = 0

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    // Logica Negocio
                    val data = result.data

                    EBaseDeDatos.tablaMarcaReloj.actualizarMarcaReloj(
                        data?.getStringExtra("nombreModificado").toString(),
                        data?.getStringExtra("paisOrigenModificado").toString(),
                        data?.getStringExtra("fechaFunModificada").toString(),
                        data?.getStringExtra("ratingfModificado")?.toInt() ?: 0,
                        data?.getIntExtra("idDevuelto",0)!!
                    )

                    actualizarListView()

                    /*
                    arreglo.get(idItemSeleccionado).nombre = data?.getStringExtra("nombreModificado").toString()
                    arreglo.get(idItemSeleccionado).paisOrigen = data?.getStringExtra("paisOrigenModificado").toString()
                    arreglo.get(idItemSeleccionado).setFechaFundacion(data?.getStringExtra("fechaFunModificada").toString())
                    arreglo.get(idItemSeleccionado).ratingPopularidad = data?.getStringExtra("ratingfModificado")?.toInt() ?: 0
                    actualizarListView()

                     */

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

                    EBaseDeDatos.tablaMarcaReloj.crearMarcaReloj(
                        data?.getStringExtra("nombreModificado").toString(),
                        data?.getStringExtra("paisOrigenModificado").toString(),
                        data?.getStringExtra("fechaFunModificada").toString(),
                        data?.getStringExtra("ratingfModificado")?.toInt() ?: 0
                    )

                    actualizarListView()

                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        if (EBaseDeDatos.tablaMarcaReloj == null) {
            EBaseDeDatos.tablaMarcaReloj = ESqliteHelperMarcaReloj(this)
            EBaseDeDatos.tablaMarcaReloj?.crearMarcaReloj("Casio", "Peru", "12/12/1991", 7)
        }

         */

        actualizarListView()

        val crearMarcaRelojButton = findViewById<Button>(R.id.btn_crear_mr)
        crearMarcaRelojButton.setOnClickListener() {
            abrirActividad(CrearMarcaReloj::class.java)
        }

    }

    fun actualizarListView(){

        this.arregloMarcaReloj = EBaseDeDatos.tablaMarcaReloj.consultarTodasLasMarcasReloj()

        val listViewMR = findViewById<ListView>(R.id.lv_marca_reloj)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloMarcaReloj
        )

        listViewMR.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listViewMR)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onResume() {
        super.onResume()
        actualizarListView()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar ->{
                abrirConParametrosEditarMR(EditarMarcaReloj::class.java)
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogoEliminar()
                actualizarListView()
                return true
            }
            R.id.mi_ver_relojes ->{

                try {
                    abrirActividadListarR(ListViewRelojes::class.java)
                } catch (e: Exception) {
                    val errorMessage = "Ocurrió un error: ${e.message}"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }


                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    fun abrirConParametrosEditarMR(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        val marcaReloj = arregloMarcaReloj.get(idItemSeleccionado)

        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

        intentExplicito.putExtra("id", marcaReloj.id)
        intentExplicito.putExtra("nombre", marcaReloj.nombre)
        intentExplicito.putExtra("paisOrigen", marcaReloj.paisOrigen)
        intentExplicito.putExtra("fechaFun", formatoFecha.format(marcaReloj.fechaFundacion))
        intentExplicito.putExtra("rating", marcaReloj.ratingPopularidad)


        callbackContenidoIntentExplicito.launch(intentExplicito)

    }

    fun abrirActividadListarR(clase: Class<*>){
        val intentExplicito = Intent(this, clase)


        intentExplicito.putExtra("idMarcaReloj", arregloMarcaReloj.get(idItemSeleccionado).id?: 0)
        intentExplicito.putExtra("nombreMarca", arregloMarcaReloj.get(idItemSeleccionado).nombre)

        startActivity(intentExplicito)
    }

    fun abrirActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        callbackContenidoIntentExplicito1.launch(intent)
    }

    fun abrirDialogoEliminar(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton("Aceptar") {
                dialog, which ->
            EBaseDeDatos.tablaMarcaReloj.eliminarMarcaReloj(arregloMarcaReloj.get(idItemSeleccionado).id?: 0)
            actualizarListView()

        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()


    }



}