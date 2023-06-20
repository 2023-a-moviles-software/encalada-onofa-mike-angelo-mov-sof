package menus.menuMarcaReloj

import MarcaReloj
import menus.MenuReloj.MenuR
import java.util.*

class Opcion4SeleccionarR: ComandoMR {
    override fun ejecutar(marcaReloj: MarcaReloj) {
        var scanner: Scanner = Scanner(System.`in`)

        print("Ingrese el ID del reloj: ")
        var indice: Int = scanner.nextInt()

        MenuR.ejecutarMenu(marcaReloj.getRelojByIndex(indice))

    }

}