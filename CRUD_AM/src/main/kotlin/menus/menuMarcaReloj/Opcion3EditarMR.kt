package menus.menuMarcaReloj

import MarcaReloj
import java.text.SimpleDateFormat
import java.util.*


class Opcion3EditarMR: ComandoMR {
    override fun ejecutar(marcaReloj: MarcaReloj) {

        val scanner = Scanner(System.`in`)
        val scanner2 = Scanner(System.`in`)

        println("Qué desea editar")
        println("1. Nombre")
        println("2. País de origen")
        println("3. Fecha de fundación")
        println("4. Rating Popularidad")

        var opcion: Int = scanner.nextInt()

        when(opcion){
            (1) -> {
                print("Ingrese el nombre: ")
                var nombre: String = scanner2.nextLine()
                marcaReloj.setNombre(nombre)
            }
            (2) -> {
                print("Ingrese el país de origen: ")
                var pais: String = scanner2.nextLine()
                marcaReloj.setPaiOrigen(pais)
            }
            (3) -> {
                print("Ingrese la fecha de fundación: ")
                var fechaFundacion: String = scanner2.nextLine()
                val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
                val fechaFudacionDate: Date = formatoFecha.parse(fechaFundacion)
                marcaReloj.setFechaFundacion(fechaFudacionDate)
            }
            (4) -> {
                print("Ingrese el rating popularidad (1-5): ")
                var ratingPopularidad: Int = scanner.nextInt()
                marcaReloj.setRatingPopularidad(ratingPopularidad)
            }

            else ->{

            }
        }

        AlmacenamientoDatos.guardarDatos()

    }

}