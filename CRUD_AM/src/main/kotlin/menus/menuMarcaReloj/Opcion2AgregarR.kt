package menus.menuMarcaReloj

import MarcaReloj
import Reloj
import java.text.SimpleDateFormat
import java.util.*


class Opcion2AgregarR: ComandoMR {
    override fun ejecutar(marcaReloj: MarcaReloj) {
        val scanner = Scanner(System.`in`)
        val scanner2 = Scanner(System.`in`)

        print("Ingrese el modelo: ")
        val modelo: String = scanner.nextLine()
        print("Ingrese el precio: ")
        val precio: Double = scanner.nextDouble()

        println("Ingrese si es digital")
        println("1. Si")
        println("2. No")
        val digital: Int = scanner.nextInt()
        var digitalBoolean: Boolean = false

        when(digital){
            (1) -> {
                digitalBoolean = true
            }
            (2) -> {
                digitalBoolean = false
            }
            else -> {
                digitalBoolean = false
            }
        }


        println("Ingrese el tipo")
        println("P. Pulsera")
        println("B. Bolsillo")
        val tipo: String = scanner2.nextLine()
        val tipoChar: Char? = tipo.firstOrNull()

        print("Ingrese la fecha de fabricación: ")
        val fechaFabricacion: String = scanner2.nextLine()

        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        val fechaFabricacionDate: Date = formatoFecha.parse(fechaFabricacion)

        marcaReloj.agregarReloj(Reloj(modelo, precio, digitalBoolean, tipoChar, fechaFabricacionDate))

        AlmacenamientoDatos.guardarDatos()

    }


}