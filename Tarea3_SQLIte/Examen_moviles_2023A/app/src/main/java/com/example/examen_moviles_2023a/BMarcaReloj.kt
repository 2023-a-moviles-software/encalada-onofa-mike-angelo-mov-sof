package com.example.examen_moviles_2023a

import java.text.SimpleDateFormat
import java.util.Date

class BMarcaReloj (nombre: String, paisOrigen: String, fechaFundacion: String, ratingPopularidad: Int){

    var id: Int? = null
    var nombre: String
    var paisOrigen: String
    var fechaFundacion: Date
    var ratingPopularidad: Int
    private var relojes: ArrayList<BReloj>

    constructor(id:Int, nombre: String, paisOrigen: String, fechaFundacion: String, ratingPopularidad: Int): this(nombre, paisOrigen, fechaFundacion, ratingPopularidad){
        this.id = id
    }

    init {
        this.nombre = nombre
        this.paisOrigen = paisOrigen
        this.fechaFundacion = StringToDate(fechaFundacion)
        this.ratingPopularidad = ratingPopularidad
        this.relojes = arrayListOf<BReloj>()
    }


    fun getRelojes(): ArrayList<BReloj>{
        return this.relojes
    }


    fun StringToDate(fecha: String): Date{
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        return formatoFecha.parse(fecha)
    }

    fun setFechaFundacion(fecha: String){
        this.fechaFundacion = StringToDate(fecha)
    }

    override fun toString(): String {
        return "id: $id, \n" +
            "Nombre: '$nombre', \n" +
                "   PaisOrigen: '$paisOrigen', \n" +
                "   FechaFundacion: $fechaFundacion, \n" +
                "   RatingPopularidad: $ratingPopularidad, \n"
    }
}