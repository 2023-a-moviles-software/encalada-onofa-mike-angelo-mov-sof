package com.example.tarea_recyvlerview_mikeencalada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val arregloRedditPost = RedditPostProveedor.redditPostList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<ImageView>(R.id.btn_searchInicio)
        searchButton.setOnClickListener() {
            abrirActividad(Recycler_list_searchItem::class.java)
        }

        inicializarRecycleView()
    }

    fun inicializarRecycleView(){
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_redditPosts
        )
        val adaptador = FRecyclerViewAdaptadorRedditPost(
            this,
            arregloRedditPost,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}