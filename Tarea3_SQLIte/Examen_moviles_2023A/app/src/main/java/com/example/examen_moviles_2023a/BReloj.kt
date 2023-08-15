package com.example.examen_moviles_2023a

import java.text.SimpleDateFormat
import java.util.Date

class BReloj ( modelo: String, precio: Double, fechaFabricacion: String) {

    var id: Int? = null
    var modelo: String
    var precio: Double
    var fechaFabricacion: Date

    constructor(id: Int, modelo: String, precio: Double, fechaFabricacion: String): this(modelo, precio, fechaFabricacion){
        this.id = id
    }

    init{
        this.modelo = modelo
        this.precio = precio
        this.fechaFabricacion = StringToDate(fechaFabricacion)
    }

    fun StringToDate(fecha: String): Date{
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        return formatoFecha.parse(fecha)
    }

    fun setFechaFabricacion(fecha: String){
        this.fechaFabricacion = StringToDate(fecha)
    }

    override fun toString(): String {
        return "id: $id, \n" +
                "modelo='$modelo', \n" +
                "   precio=$precio, \n" +
                "   fechaFabricacion=$fechaFabricacion\n"
    }

}