package com.example.tarea_recyvlerview_mikeencalada


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FRecyclerViewAdaptadorRedditPost(
    private val contexto: MainActivity,
    private val lista: ArrayList<RedditPost>,
    private val recyclerView: RecyclerView

) : RecyclerView.Adapter<FRecyclerViewAdaptadorRedditPost.MyViewHolder>(){

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombreUsuarioTextView: TextView
        val tiempoPublicacionTextView: TextView
        val descripcionTextView:TextView
        val imagenImageView: ImageView
        val numLikesTextView: TextView
        val numCommentsTextView: TextView
        val numSharesTextView: TextView
        init {
            nombreUsuarioTextView = view.findViewById(R.id.tv_userName)
            tiempoPublicacionTextView = view.findViewById(R.id.tv_horasPost)
            descripcionTextView = view.findViewById(R.id.tv_descripcion)
            imagenImageView = view.findViewById(R.id.iv_imagenPost)
            numLikesTextView = view.findViewById(R.id.tv_numLikes)
            numCommentsTextView = view.findViewById(R.id.tv_numComments)
            numSharesTextView = view.findViewById(R.id.tv_numShares)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycle_view_list_rp,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val redditPostActual = this.lista[position]
        holder.nombreUsuarioTextView.text = redditPostActual.nombreUsuario
        holder.tiempoPublicacionTextView.text = redditPostActual.tiempoPublicacion
        holder.descripcionTextView.text = redditPostActual.descripcion
        holder.numLikesTextView.text = redditPostActual.numLikes
        holder.numCommentsTextView.text = redditPostActual.numComments
        holder.numSharesTextView.text = redditPostActual.numShares
        Glide.with(holder.imagenImageView.context).load(redditPostActual.imagen).into(holder.imagenImageView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }
}