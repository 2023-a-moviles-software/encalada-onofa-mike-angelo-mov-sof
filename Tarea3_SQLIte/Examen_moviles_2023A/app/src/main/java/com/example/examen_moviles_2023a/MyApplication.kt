package com.example.examen_moviles_2023a

import android.app.Application

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        EBaseDeDatos.inicializar(this)
        //EBaseDeDatos.tablaMarcaReloj.crearReloj("RDD", 7.8, "11/11/2001", 1)
    }
}