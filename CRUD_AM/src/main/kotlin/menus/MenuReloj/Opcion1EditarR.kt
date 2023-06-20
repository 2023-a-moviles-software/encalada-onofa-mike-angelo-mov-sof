package menus.MenuReloj

import Reloj
import java.text.SimpleDateFormat
import java.util.*

class Opcion1EditarR: ComandoR {
    override fun ejecutar(reloj: Reloj) {
        val scanner = Scanner(System.`in`)
        val scanner1 = Scanner(System.`in`)

        println("Qué desea editar")
        println("1. Modelo")
        println("2. Precio")
        println("3. Digital")
        println("4. Tipo")
        println("5. Fecha Fabricación")

        var opcion: Int = scanner.nextInt()

        when(opcion){
            (1) -> {
                print("Ingrese el modelo: ")
                var nombre: String = scanner1.nextLine()
                reloj.setModelo(nombre)
            }
            (2) -> {
                print("Ingrese el precio: ")
                var precio: Double = scanner.nextDouble()
                reloj.setPrecio(precio)
            }
            (3) -> {
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

                reloj.setEsDigital(digitalBoolean)
            }
            (4) -> {
                println("Ingrese el tipo")
                println("P. Pulsera")
                println("B. Bolsillo")
                val tipo: String = scanner1.nextLine()
                val tipoChar: Char? = tipo.firstOrNull()

                reloj.setTipo(tipoChar)
            }

            (5) -> {
                println("Ingrese la fecha de fabricación")
                val fechaFabricacion: String = scanner1.nextLine()

                val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
                val fechaFabricacionDate: Date = formatoFecha.parse(fechaFabricacion)

                reloj.setFechaFabricacion(fechaFabricacionDate)
            }

            else ->{

            }
        }

        AlmacenamientoDatos.guardarDatos()
    }


}