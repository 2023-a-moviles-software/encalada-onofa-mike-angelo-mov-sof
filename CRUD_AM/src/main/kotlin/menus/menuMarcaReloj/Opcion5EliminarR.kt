package menus.menuMarcaReloj

import MarcaReloj
import java.util.*

class Opcion5EliminarR: ComandoMR {
    override fun ejecutar(marcaReloj: MarcaReloj) {

        val scanner = Scanner(System.`in`);

        println("Ingrese el índice del reloj:")
        val indice = scanner.nextInt()

        marcaReloj.elminarRelojByIndex(indice)

        AlmacenamientoDatos.guardarDatos()
    }
}