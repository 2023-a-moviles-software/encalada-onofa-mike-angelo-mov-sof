package com.example.examen_moviles_2023a

import java.text.SimpleDateFormat
import java.util.Date

class BReloj (modelo: String, precio: Double, fechaFabricacion: String) {

    var modelo: String
    var precio: Double
    var fechaFabricacion: Date

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
        return "modelo='$modelo', \n" +
                "   precio=$precio, \n" +
                "   fechaFabricacion=$fechaFabricacion\n"
    }

}