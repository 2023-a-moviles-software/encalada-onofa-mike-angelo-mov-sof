package com.example.proyectomoviles2023a

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class NotaRecordatorio(
    var id: String,
    var tipo: String,
    var titulo: String,
    var descripcion: String,
    var categoria: String,
    var recordatorio: Calendar?
) {

    fun getRecordatorioString(): String{
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return formatoFecha.format(this.recordatorio?.time ?: null )
    }

    fun setRecordatorioCalendar(fecha: String) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        try {
            val date = dateFormat.parse(fecha)
            val calendar = Calendar.getInstance()
            calendar.time = date
            this.recordatorio = calendar
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}