package com.example.tarea_recyvlerview_mikeencalada

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FRecyclerViewAdaptadorRedditSearchItem(
    private val contexto: Recycler_list_searchItem,
    private val lista: ArrayList<RedditSearchItem>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<FRecyclerViewAdaptadorRedditSearchItem.MyViewHolder>() {

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tituloTextView: TextView
        val descripcionTextView: TextView
        val userTextView: TextView
        val imagenSearchItemImageView: ImageView

        init {
            tituloTextView = view.findViewById(R.id.tv_titulo_si)
            descripcionTextView = view.findViewById(R.id.tv_descripcion_si)
            userTextView = view.findViewById(R.id.tv_user_si)
            imagenSearchItemImageView = view.findViewById(R.id.iv_imagen_si)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FRecyclerViewAdaptadorRedditSearchItem.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycle_view_list_si,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: FRecyclerViewAdaptadorRedditSearchItem.MyViewHolder,
        position: Int
    ) {
        val redditSearchItemActual = this.lista[position]
        holder.tituloTextView.text = redditSearchItemActual.titulo
        holder.descripcionTextView.text = redditSearchItemActual.descripcionBusqueda
        holder.userTextView.text = redditSearchItemActual.usuario
        Glide.with(holder.imagenSearchItemImageView.context).load(redditSearchItemActual.imagen).into(holder.imagenSearchItemImageView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }
}