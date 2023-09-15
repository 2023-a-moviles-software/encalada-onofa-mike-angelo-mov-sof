package com.example.proyectomoviles2023a


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerViewAdaptorNotaRecordatorio(
    private val contexto: InicioActivity,
    private val lista: ArrayList<NotaRecordatorio>,
    private val recyclerView:RecyclerView
): RecyclerView.Adapter<RecyclerViewAdaptorNotaRecordatorio.MyViewHolder>() {



    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        private var authActual = FirebaseAuth.getInstance().currentUser
        val tituloTextView: TextView
        val descripcionTextView: TextView
        val categoriaTextView: TextView
        val recordatorioTextView: TextView
        val recordatorioTituloTextView: TextView
        val botonEliminar: ImageView
        val botonEditar: ImageView

        init {
            tituloTextView = view.findViewById(R.id.tv_rvnr_tituloE)
            descripcionTextView = view.findViewById(R.id.tv_rvnr_descripcionE)
            categoriaTextView = view.findViewById(R.id.tv_rvnr_categoriaE)
            recordatorioTextView = view.findViewById(R.id.tv_rvnr_recordatorioE)
            recordatorioTituloTextView = view.findViewById(R.id.tv_rvnr_recordatorioT)

            botonEliminar = view.findViewById<ImageView?>(R.id.iv_rvnr_eliminar)
            botonEliminar.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    abrirDialogoEliminar(position)
                    notifyDataSetChanged()
                    //contexto.consultarNotasRecordatorios(this@RecyclerViewAdaptorNotaRecordatorio)
                }
            }

            botonEditar = view.findViewById(R.id.im_rvnr_editar)
            botonEditar.setOnClickListener {

            }

        }

        fun abrirDialogoEliminar(position: Int){
            val builder = AlertDialog.Builder(contexto)
            builder.setTitle("Desea Eliminar?")
            builder.setPositiveButton("Aceptar") {
                    dialog, which ->
                val notaSeleccionada = lista[position]
                val db = Firebase.firestore
                //val notaRef= db.collection("notas_recordatorios").document(notaSeleccionada.id!!)
                val notaRef= db.collection("usuarios").document(authActual!!.uid).collection("notas_recordatorios").document(notaSeleccionada.id!!)
                lista.removeAt(position)
                notifyDataSetChanged()
                notaRef.delete()
                    .addOnSuccessListener {  }
                    .addOnFailureListener {  }

            }
            builder.setNegativeButton("Cancelar", null)
            val dialogo = builder.create()
            dialogo.show()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_notarecordatorio,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val notaRecordatorioActual = this.lista[position]
        holder.tituloTextView.text = notaRecordatorioActual.titulo
        holder.descripcionTextView.text = notaRecordatorioActual.descripcion
        holder.categoriaTextView.text = notaRecordatorioActual.categoria
        if(notaRecordatorioActual.recordatorio == null){
            holder.recordatorioTituloTextView.visibility = View.INVISIBLE
            holder.recordatorioTextView.visibility = View.INVISIBLE
        }else{
            holder.recordatorioTextView.text = notaRecordatorioActual.getRecordatorioString()
        }

    }

    override fun getItemCount(): Int {
        return this.lista.size
    }
}



