package menus.menuListaMarcaReloj

import AlmacenamientoDatos
import ListaMarcaReloj
import MarcaReloj
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Scanner

class Opcion2AgregarMR: Comando {
    override fun ejecutar() {

        val scanner = Scanner(System.`in`)

        print("Ingrese el nombre: ")
        var nombre: String = scanner.nextLine()
        print("Ingrese el país de origen: ")
        var paisOrigen: String = scanner.nextLine()
        print("Ingrese la fecha de fundación: ")
        var fechaFundacion: String = scanner.nextLine()
        print("Ingrese el rating popularidad (1-5): ")
        var ratingPopularidad: Int = scanner.nextInt()

        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        val fechaFudacionDate: Date = formatoFecha.parse(fechaFundacion)

        ListaMarcaReloj.agregarMarcaReloj(MarcaReloj(nombre, paisOrigen, fechaFudacionDate, ratingPopularidad))

        AlmacenamientoDatos.guardarDatos()
    }
}