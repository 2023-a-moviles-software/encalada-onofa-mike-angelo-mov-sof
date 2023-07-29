package com.example.tarea_recyvlerview_mikeencalada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class Recycler_list_searchItem : AppCompatActivity() {

    val arregloRedditSearchItem = RedditSearchItemProveedor.redditSearchItemList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_list_search_item)

        inicializarRecycleView()

    }

    private fun inicializarRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_searchitems)

        val adaptador = FRecyclerViewAdaptadorRedditSearchItem(
            this,
            arregloRedditSearchItem,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

    }
}